package com.fyrj.framework.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fyrj.framework.utils.SpringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fyrj.framework.utils.JsonMapper;

/***
 * 检查系统是否有使用redis，避免每次使用系统都必须要去安装redis客户端
 * @author ying.cai
 * @date 2016-5-14 上午09:06:50
 * @version 1.0
 */
@Component
public class RedisUtil {

	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;
	@Resource
	private RedisTemplate<Serializable, Object> incRedisTemplate;
	
	/**系统是否有使用redis*/
	public static boolean isUseRedis(){
		return SpringUtils.getApplicationContext().containsBean("redisTemplate");
	}
	
	/**获取redis工具类实例*/
	public static RedisUtil getInstance() {
		return SpringUtils.getApplicationContext().getBean(RedisUtil.class);
	}
	
	/**自增操作的get方法*/
	public Object incGet(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = incRedisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	/**读取缓存值*/
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	/**读取缓存 */
	public <T>T get(final String key,Class<T> cls) {
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//		T result =JsonMapper.fromJsonString(operations.get(key).toString(), cls) ;
		T result = JSONObject.parseObject(operations.get(key).toString(), cls);
		return result;
	}
	
	/**集合获取 */
	public <T>T get(final String key,Class<?> collectionClass,Class<?> ...classes){
		String value=get(key,String.class);
		return JsonMapper.json2Collection(value,collectionClass, classes);
	}
	
	/**自增*/
	public Long increment(String key, long value){
		return redisTemplate.opsForValue().increment(key,value);
	}
	
	/**自减 */
	public Long decrease(String key, long value){
		return redisTemplate.opsForValue().increment(key,-value);
	}
	
	/**批量删除对应的value*/
	public void remove(final String... keys) {
		redisTemplate.delete(keys);
	}
	
	/**删除单个对应value*/
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}
	
	/**判断缓存中是否有对应的value*/
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}
	
	/**批量删除key*/
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			redisTemplate.delete(keys);
		}
	}
	
	/**写入缓存 */
	public boolean set(final String key, Object value) {
		return set(key, value, null);
	}
	
	/**写入缓存带有效期 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			if(expireTime!=null){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**设置指定key的过期时间 */
	public void setExpire(String key, Long expireTime) {
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}
	
	/**写入list 永久缓存*/
	public Long pushList(final String key, Object value){
		Long res = (long) 0;
		try {
			res = redisTemplate.opsForList().leftPush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**入栈 （redis写入格式为list）*/
	public Long pushList(final String key, Object value, Long expireTime) {
		Long res = (long) 0;
		try {
			res = redisTemplate.opsForList().leftPush(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/** 检索  */
	public List<Object> rangeList(final String key, int start, int end) {
		List<Object> list = null;
		try {
			list = redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**若存在则不写入且返回false，反之亦然*/
	public boolean setNX(String key, Object value) {
		boolean result = false;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.setIfAbsent(key, value);
		return result;
	}
	
	/** 返回key对应的旧值且写入新value，若key不存在，则返回null */
	public Object getAndSet(String key, Object value) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.getAndSet(key, value);
		return result;
	}
}

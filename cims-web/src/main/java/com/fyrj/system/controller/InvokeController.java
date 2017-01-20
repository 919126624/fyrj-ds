package com.fyrj.system.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fyrj.basedata.api.dto.City;
import com.fyrj.basedata.api.service.CityApiService;
import com.fyrj.framework.mq.service.MqMessageDto;
import com.fyrj.framework.utils.RedisLock;
import com.fyrj.framework.utils.RedisUtil;

@Controller
@RequestMapping("invoke/*")
public class InvokeController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int stockNum = 100;
	@Autowired
	private CityApiService cityApiService;
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping("index")
	public String invokeTest(){
		return "invoke/index";
	}
	
	@RequestMapping("findCity")
	@ResponseBody
	public City findCity(){
		City city = cityApiService.findCity("xxx", 1);
		return city;
	}
	
	/***
	 * 向redis集群中写入数据
	 * @param key
	 * @param value
	 */
	@RequestMapping("redisPut")
	@ResponseBody
	public JSONObject redisPut(String key , String value){
		JSONObject obj = new JSONObject();
		try {
			redisUtil.set(key, value);
			obj.put("value", "操作成功！");
		} catch (Exception e) {
			obj.put("value", "操作失败！");
			e.printStackTrace();
		}
		return obj;
	}
	/***
	 * 向redis集群中获取数据
	 * @param key
	 * @param value
	 */
	@RequestMapping("redisGet")
	@ResponseBody
	public JSONObject redisGet(String key){
		JSONObject result = new JSONObject();
		try {
			Object value = redisUtil.get(key);
			value = value==null?"暂无此key对应数据！":value;
			result.put("value", value);
		} catch (Exception e) {
			result.put("value", "获取值失败！");
			e.printStackTrace();
		}
		
		return result;
	}

	/***
	 * 所有redisApi的测试
	 */
	@ResponseBody
	@RequestMapping("redisAllTest")
	public void redisAllTest(){
		RedisTestObj obj = new RedisTestObj(UUID.randomUUID().toString(), new Date(), new BigDecimal("12.8888"), 8.88);
		RedisTestObj obj2 = new RedisTestObj(UUID.randomUUID().toString(), new Date(), new BigDecimal("11.8888"), 8.88);
		RedisTestObj obj3 = new RedisTestObj(UUID.randomUUID().toString(), new Date(), new BigDecimal("10.8888"), 8.88);
//		String key1 = "test:key1";
//		String value1 = "test-key1-value1";
//		redisUtil.set(key1, value1);
//		String key1_value1 = (String) redisUtil.get(key1);
//		System.out.println(key1_value1);
//		
//		redisUtil.set("test:obj1", obj);
//		redisUtil.set("test:obj2", JSONObject.toJSONString(obj));
//		RedisTestObj objValue = (RedisTestObj)redisUtil.get("test:obj1");
//		RedisTestObj objValue2 = redisUtil.get("test:obj2", RedisTestObj.class);
		
		
//		List<String> myList1 = new ArrayList<>();
//		myList1.add("A");
//		myList1.add("B");
//		List<RedisTestObj> myList2 = new ArrayList<>();
//		myList2.add(obj);
//		myList2.add(obj2);
//		redisUtil.set("test:myList1", JSONArray.toJSONString(myList1));
//		redisUtil.set("test:myList2", JSONArray.toJSONString(myList2));
//		
//		List<String> myListValue1 = redisUtil.get("test:myList1", ArrayList.class, String.class);
//		List<RedisTestObj> myListValue2 = redisUtil.get("test:myList2", ArrayList.class, RedisTestObj.class);
//		System.out.println("over==================");
		
//		redisUtil.increment("test:increment", 1);
//		redisUtil.decrease("test:increment", 3);
//		System.out.println(redisUtil.incGet("test:increment"));
		
//		redisUtil.pushList("test:pushList1", obj);
//		redisUtil.pushList("test:pushList1", obj2);
//		
//		Object listObj = redisUtil.rangeList("test:pushList1", 0, 8);
//		
//		redisUtil.pushList("test:pushList1", obj3);
//		Object listObj2 = redisUtil.rangeList("test:pushList1", 0, 8);
		
//		boolean result = redisUtil.setNX("test:nx", "cy");
//		boolean result2 = redisUtil.setNX("test:nx", "cy");
		
//		Object getAndSetValue = redisUtil.getAndSet("test:getAndSet", "cy2");
		System.out.println(Thread.currentThread());
		System.out.println("剩下还有list set还有序列号性能对比等等以后再测！");
	}
	
	/***
	 * 分布式锁测试，
	 * 用jmeter工具模拟多线程，对固定数据库存(竞争资源)进行扣减，看是否超出
	 * @throws InterruptedException 
	 */
	@ResponseBody
	@RequestMapping("testRedisAndZookeeperLock")
	public void testRedisAndZookeeperLock() throws InterruptedException{
		final Random random = new Random();
		//redis锁测试
		long startTime = System.currentTimeMillis();
		buy(1+random.nextInt(5));
		long endTime = System.currentTimeMillis();
		System.out.println("redis锁扣减库存耗时："+(endTime-startTime)+"毫秒");
		//zookeeper锁测试
		
	}
	
//	private void testRedisLock(){
//		final Random random = new Random();
//		ExecutorService executor = Executors.newFixedThreadPool(400);
//		for (int i = 0; i < 300; i++) {
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						buy(1+random.nextInt(5));
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}
//	}
	
	/**模拟下单
	 * @throws InterruptedException */
	private void buy(int buyNum) throws InterruptedException{
		if(reduceNum(buyNum)){
			System.out.println("线程:"+Thread.currentThread()+"下单success!数量为：" + buyNum +",执行操作后，库存剩余："+stockNum);
		}else{
			System.out.println("线程:"+Thread.currentThread()+"下单Faild!数量为：" + buyNum +",执行操作后，库存剩余："+stockNum);
		}
	}

	/**模拟扣减库存，num为要扣减的库存数
	 * @throws InterruptedException */
	private boolean reduceNum(int num) throws InterruptedException{
		String lockKey = "lock:reduceNum";
		RedisLock lock = new RedisLock(redisUtil, lockKey);
		boolean isOk = false;
		if(lock.acquire()){
			int currentStockNum = getStockNum();
			if(currentStockNum>=num){
				stockNum-=num;
				isOk =  true;
			}
			lock.release();
		}
		return isOk;
	}
	
	/**模拟取得库存数*/
	private int getStockNum(){
		return stockNum;
	}
	
	
	@RequestMapping("sendMqMessage")
	@ResponseBody
	public void sendMqMessage(String routingKey,String message){
		MqMessageDto<String> dto = new MqMessageDto<>();
		dto.setLabel("cims-app send");
		dto.setDescribe("简单消息描述");
		dto.setData(message);
		//rabbitTemplate.convertAndSend(routingKey, dto);
		rabbitTemplate.convertAndSend("direct_queue1", dto);
		rabbitTemplate.convertAndSend("direct_queue2", dto);
		rabbitTemplate.convertAndSend("topic_queue_exchange","topic.xxl", dto);
		rabbitTemplate.convertAndSend("topic_queue_exchange","xxx.queue", dto);
		rabbitTemplate.convertAndSend("fanout_queue_exchange", "", dto);
	}
	
}

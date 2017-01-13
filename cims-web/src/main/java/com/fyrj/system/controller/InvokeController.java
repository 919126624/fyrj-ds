package com.fyrj.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fyrj.basedata.api.dto.City;
import com.fyrj.basedata.api.service.CityApiService;
import com.fyrj.framework.utils.RedisUtil;

@Controller
@RequestMapping("invoke/*")
public class InvokeController {
	@Autowired
	private CityApiService cityApiService;
	@Autowired
	private RedisUtil redisUtil;
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
}

package com.fyrj.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyrj.basedata.api.dto.City;
import com.fyrj.basedata.api.service.CityApiService;

@Controller
@RequestMapping("invoke/*")
public class InvokeController {
	@Autowired
	private CityApiService cityApiService;
	
	@RequestMapping("invokeTest")
	public String invokeTest(){
		return "invoke/invokeTest";
	}
	
	@RequestMapping("findCity")
	@ResponseBody
	public City findCity(){
		City city = cityApiService.findCity("xxx", 1);
		return city;
	}
}

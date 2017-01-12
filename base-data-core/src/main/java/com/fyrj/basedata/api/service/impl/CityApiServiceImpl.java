package com.fyrj.basedata.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fyrj.basedata.api.dto.City;
import com.fyrj.basedata.api.service.CityApiService;
import com.fyrj.basedata.service.CityService;

public class CityApiServiceImpl implements CityApiService{
	
	@Autowired
	private CityService cityService;
	
	@Override
	public City findCity(String id, int type) {
		return cityService.findCity(id);
	}

}

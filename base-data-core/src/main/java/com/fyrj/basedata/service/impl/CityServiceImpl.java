package com.fyrj.basedata.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fyrj.basedata.api.dto.City;
import com.fyrj.basedata.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Override
	public City findCity(String id) {
		City city = new City();
		city.setId(UUID.randomUUID().toString());
		city.setCreateTime(new Date());
		city.setName("益阳");
		return city;
	}

}

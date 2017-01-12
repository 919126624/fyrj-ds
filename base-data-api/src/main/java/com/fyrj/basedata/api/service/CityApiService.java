package com.fyrj.basedata.api.service;

import com.fyrj.basedata.api.dto.City;

public interface CityApiService {
	
	City findCity(String id,int type);
}

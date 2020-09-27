package com.itstyle.es.city.service;

import com.itstyle.es.city.entity.City;

public interface CityService {
	/**
	 * 新增城市
	 * @param log
	 * @return
	 */
	void saveCity(City city);
   
	void getNearbyCities(double lat, double lon);
}

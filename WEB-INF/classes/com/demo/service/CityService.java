package com.demo.service;

import java.util.List;

import com.demo.pojo.City;

public interface CityService {
	/**
	 * 获取所有城市
	 * @return 城市list集合
	 */
	public 	List<City> queryCityList();
}

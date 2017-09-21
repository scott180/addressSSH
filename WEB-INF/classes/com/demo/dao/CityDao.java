package com.demo.dao;

import java.util.List;

import com.demo.pojo.City;


public interface CityDao {
	/**
	 * 获取所有城市
	 * @return  城市集合
	 */
	public 	List<City> queryCityList();
}

package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.CityDao;
import com.demo.pojo.City;
import com.demo.service.CityService;

public class CityServiceImpl implements CityService {
	
	private CityDao cityDao;
	
	public CityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	/**
	 * 城市列表
	 */
	@Override
	public List<City> queryCityList() {
		// TODO Auto-generated method stub
		return cityDao.queryCityList();
	}

}

package com.demo.action;

import java.util.List;

import com.demo.pojo.City;
import com.demo.service.CityService;

public class CityAction extends BaseAction {
	private CityService cityService;
	private List<City> list;
	
	/**
	 * 城市列表
	 */
	public String queryCityList(){
		list = cityService.queryCityList();
		return "success";
	}

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	
}

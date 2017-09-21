package com.demo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.CityDao;
import com.demo.pojo.City;

public class CityDaoImpl implements CityDao {
	@Autowired
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 城市列表
	 */
	@Override
	public List<City> queryCityList() {
		// TODO Auto-generated method stub
		String hql = "from City";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<City> list = q.list();
		return list;
	}

}

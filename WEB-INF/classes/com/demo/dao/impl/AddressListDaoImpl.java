package com.demo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.AddressListDao;
import com.demo.pojo.AddressList;
import com.demo.pojo.User;

public class AddressListDaoImpl implements AddressListDao {

	@Autowired
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<AddressList> queryAddrList(int userId, int page, int pageSize) {
		// TODO Auto-generated method stub
		page--;
//		String hql = "from User u where u.id = "+userId;  //from Student s left outer join fetch s.classes
		String hql = "from AddressList a left outer join fetch a.user  where a.user.id="+userId; 
		System.out.println(hql);
		Query q = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(page*pageSize).setMaxResults(pageSize);
		List<AddressList> addrs = q.list();
		
		return addrs;
	}

	@Override
	public void insertAddr(AddressList addr) {
		// TODO Auto-generated method stub
		addr.setId(null);
		sessionFactory.getCurrentSession().saveOrUpdate(addr);
		
	}

	@Override
	public void updateAddr(AddressList addr) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(addr);
	}

	@Override
	public void deleteAddr(AddressList addr) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(addr);
	}

	@Override
	public int queryAddrCount(int userId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from AddressList a where a.user.id = "+userId;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Long num = (Long)q.uniqueResult();
		return Integer.parseInt(num.toString());
	}

	@Override
	public AddressList queryAddr(int id) {
		// TODO Auto-generated method stub
		String hql = "from AddressList a where a.id="+id;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<AddressList> list = q.list();
		return list.get(0);
	}

	@Override
	public List<String> checkNameAddr(int userId,int addrId) {
		// TODO Auto-generated method stub
		String hql = "select a.name from AddressList a where a.user.id="+userId+" and a.id!="+addrId;
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

}

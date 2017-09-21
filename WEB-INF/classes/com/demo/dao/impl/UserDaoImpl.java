package com.demo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.dao.UserDao;
import com.demo.pojo.User;

public class UserDaoImpl  implements UserDao  {

	@Autowired
	public SessionFactory sf;
	 
	public SessionFactory getSessionFactory() {
		return sf;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}
	
	/**
	 *  用户列表
	 */
	@Override
	public List<User> queryUserList(int page, int pageSize) {
		// TODO Auto-generated method stub
		page--;
		String hql = "from User";
		Query q = sf.getCurrentSession().createQuery(hql).setFirstResult(page*pageSize).setMaxResults(pageSize);
		List<User> lists = (List<User>)q.list();
		
		return lists;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		user.setId(null);
		sf.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from User u where u.id ="+id;
		Query q=sf.getCurrentSession().createQuery(hql);
		q.executeUpdate();
		
		String hql2="delete from AddressList a where a.user.id="+id;
		Query q2=sf.getCurrentSession().createQuery(hql2);
		q2.executeUpdate();
	}

	@Override
	public boolean checkUser(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *  用户总数量
	 */
	@Override
	public int queryUserCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) FROM User";
		Query q=sf.getCurrentSession().createQuery(hql);
		Long t = (Long)q.uniqueResult();
		return Integer.parseInt(t.toString());
	}

	@Override
	public List<String> checkName(int userId) {
		// TODO Auto-generated method stub
		String hql = "select u.name from User u where u.id!="+userId;
		Query q=sf.getCurrentSession().createQuery(hql);
		return q.list();
	}

}

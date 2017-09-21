package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.UserDao;
import com.demo.pojo.User;
import com.demo.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> queryUserList(int page, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.queryUserList(page, pageSize);
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
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
		return userDao.queryUserCount();
	}

	@Override
	public boolean checkName(int userId, String name) {
		// TODO Auto-generated method stub
		List<String> names = userDao.checkName(userId);
		boolean flag =false;
		for(String n : names){
			if(n.equals(name)){
				flag=true;break;
			}
		}
		return flag;
	}

}

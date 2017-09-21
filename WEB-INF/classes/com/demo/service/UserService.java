package com.demo.service;

import java.util.List;

import com.demo.pojo.User;

public interface UserService {
	/**
	 * 获取用户集合
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<User> queryUserList(int page,int pageSize);
	/**
	 * 添加人员
	 * @param user
	 */
	public void insertUser(User user);
	/**
	 * 修改人员
	 * @param user
	 */
	public void updateUser(User user);
	/**
	 * 删除人员
	 * @param user
	 */
	public void deleteUser(int id);
	/**
	 * 验证重名
	 * @param name
	 * @return 存在返回false,不存在返回true
	 */
	public boolean checkUser(String name);
	
	/**
	 *  用户总数量
	 */
	public int queryUserCount();
	
	public boolean checkName(int userId, String name);
}

package com.demo.dao;

import java.util.List;

import com.demo.pojo.AddressList;

public interface AddressListDao {
	/**
	 * 获取通讯录集合
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AddressList> queryAddrList(int userId,int page,int pageSize);
	/**
	 * 添加通讯录
	 * @param addr
	 */
	public void insertAddr(AddressList addr);
	/**
	 * 修改通讯录
	 * @param addr
	 */
	public void updateAddr(AddressList addr);
	/**
	 * 删除通讯
	 * @param addr
	 */
	public void deleteAddr(AddressList addr);
	/**
	 * 
	 * @return
	 */
	public int queryAddrCount(int userId);
	public AddressList queryAddr(int id);
	public List<String> checkNameAddr(int userId,int addrId);
}

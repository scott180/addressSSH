package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.AddressListDao;
import com.demo.pojo.AddressList;
import com.demo.service.AddressListService;

public class AddressListServiceImpl implements AddressListService {

	private AddressListDao addrDao;
	
	public AddressListDao getAddrDao() {
		return addrDao;
	}

	public void setAddrDao(AddressListDao addrDao) {
		this.addrDao = addrDao;
	}

	@Override
	public List<AddressList> queryAddrList(int userId, int page, int pageSize) {
		// TODO Auto-generated method stub
		return addrDao.queryAddrList(userId, page, pageSize);
	}

	@Override
	public void insertAddr(AddressList addr) {
		// TODO Auto-generated method stub
		addrDao.insertAddr(addr);
	}

	@Override
	public void updateAddr(AddressList addr) {
		// TODO Auto-generated method stub
		addrDao.updateAddr(addr);
	}

	@Override
	public void deleteAddr(AddressList addr) {
		// TODO Auto-generated method stub
		addrDao.deleteAddr(addr);
	}

	@Override
	public int queryAddrCount(int userId) {
		// TODO Auto-generated method stub
		return addrDao.queryAddrCount(userId);
	}

	@Override
	public AddressList queryAddr(int id) {
		// TODO Auto-generated method stub
		return addrDao.queryAddr(id);
	}

	@Override
	public boolean checkNameAddr(int userId, String name,int addrId) {
		// TODO Auto-generated method stub
		List<String> names = addrDao.checkNameAddr(userId,addrId);
		boolean flag =false;
		for(String n : names){
			if(n.equals(name)){
				flag=true;break;
			}
		}
		return flag;
	}

}

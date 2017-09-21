package com.demo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.pojo.AddrEntity;
import com.demo.pojo.AddressList;
import com.demo.pojo.User;
import com.demo.service.AddressListService;

public class AddressListAction extends BaseAction {
	public Map map = new HashMap();
	private AddressListService addrService;
	private int page;
	private String rows;
	
	private int userId;
	private String name;
	private String email;
	private String tel;
	private int addrId;
	
	private String ids;
	
	public String queryAddrList(){
		if("10".equals(rows)){
			rows="0";
		}
		int pageSize=Integer.parseInt((rows == null || rows == "0") ? "20" : rows);  
		int total = addrService.queryAddrCount(userId);
		int pageCount = getPageCount(total, pageSize);// 总页数
		page = getPage(page, pageCount);// 当前页
		List<AddressList> list = addrService.queryAddrList(userId,page, pageSize);
		List<AddrEntity> aList = new ArrayList<AddrEntity>();
		for(AddressList addr :list){
			AddrEntity ad = new AddrEntity();
			ad.setEmail(addr.getEmail());
			ad.setId(addr.getId());
			ad.setName(addr.getName());
			ad.setTel(addr.getTel());
			ad.setUserId(addr.getUser().getId());
			aList.add(ad);
		}
		map.put("total",total);
		map.put("rows", aList);
		return "success";
	}
	
	public String checkNameAddr(){
		boolean flag = addrService.checkNameAddr(userId,name,addrId);
		
		map.put("msg",flag);
		return "success";
	}
	
	public void saveOrEditAddr(){
		AddressList addr = new AddressList();
		addr.setEmail(email);
		addr.setId(addrId);
		addr.setName(name);
		addr.setTel(tel);
		addr.setUser(new User(userId));
		if(addrId==0){
			addrService.insertAddr(addr);
		}else{
			addrService.updateAddr(addr);
		}
	}
	
	
	public void delAddr(){
		String[] id = ids.split(",");
		int len = id.length;
		for(int i=0;i<len;i++){
			AddressList addr = new AddressList();
			addr = addrService.queryAddr(Integer.parseInt(id[i]));
			addrService.deleteAddr(addr);
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public AddressListService getAddrService() {
		return addrService;
	}

	public void setAddrService(AddressListService addrService) {
		this.addrService = addrService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAddrId() {
		return addrId;
	}

	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}

package com.demo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.pojo.City;
import com.demo.pojo.User;
import com.demo.pojo.UserEntity;
import com.demo.service.UserService;

public class UserAction extends BaseAction {
	private Map map = new HashMap();
	private UserService userService;
	private int page;
	private String rows;
	
	private String name;
	private int city;
	private int userId;
	private String ids;
	
	public String address(){
		return "success";
	}
	
	public String queryUserList(){
		if("10".equals(rows)){
			rows="0";
		}
		int pageSize=Integer.parseInt((rows == null || rows == "0") ? "20" : rows);  
		int total = userService.queryUserCount();//用户总数量
		int pageCount = getPageCount(total, pageSize);
		page = getPage(page, pageCount);// 页数
		List<User> list = userService.queryUserList(page, pageSize);
		List<UserEntity> uList=new ArrayList<UserEntity>();
		for(User u:list){
			UserEntity ue = new UserEntity();
			ue.setCityId(u.getCity().getId());
			ue.setCityName(u.getCity().getName());
			ue.setId(u.getId());
			ue.setName(u.getName());
			ue.setOpt(ue.getId());
			uList.add(ue);
		}
		map.put("total",total);
		map.put("rows", uList);
		return "success";
	}
	
	public void saveOrEditUser(){
		User user = new User();
		user.setName(name);
		user.setId(userId);
		user.setCity(new City(city));
		if(userId==0){
			userService.insertUser(user);
		}else{
			userService.updateUser(user);
		}
	}
	
	public void delUser(){
		String[] id = ids.split(",");
		int len = id.length;
		for(int i=0;i<len;i++){
			userService.deleteUser(Integer.parseInt(id[i]));
		}
	}

	public String checkName(){
		boolean flag = userService.checkName(userId,name);
		
		map.put("msg",flag);
		return "success";
	}
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if(userId!=null && userId.length()>0){
			this.userId = Integer.parseInt(userId);
		}
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}

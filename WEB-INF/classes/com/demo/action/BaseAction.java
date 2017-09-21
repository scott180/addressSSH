package com.demo.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware, ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2133314239402892174L;

	protected Map<String, Object> session;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected  ServletContext application;
	
	protected boolean hasMessage = false;
	
	protected String userName;
	
	protected int userId;
	
	public void setMessage(String key ,String msg){
		hasMessage = true;
		request.setAttribute(key, msg);
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.application = context;
	}

	public String toRealPath(String path) {
		return application.getRealPath(path);
	}

	public String execute() throws Exception {
		return "success";
	}

	public String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 当前页数
	 * @param page : 页数
	 * @param count ：总页数
	 * @return
	 * @author lqj
	 * @time 2013-7-4 16:11:39
	 */
	public int getPage(int page,int count){
		page=page>=count?count:page;
		page=page<=0?1:page;
		return page;
	}
	
	/**
	 * 总页数
	 * @param num
	 * @param pageSize
	 * @return
	 */
	public int getPageCount(int num,int pageSize){
		int pageCount=0;
		pageCount = num/pageSize;
		pageCount = num%pageSize==0?pageCount:pageCount+1;
		return pageCount;
	}
	
}


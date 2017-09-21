package com.demo.pojo;

import java.util.ArrayList;
import java.util.List;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private City city;
     private List addrList = new ArrayList();
     private String name;

    // Constructors


	/** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(City city, String name) {
        this.city = city;
        this.name = name;
    }
    
    /** full constructor */
    public User(City city, List addrList, String name) {
        this.city = city;
        this.addrList = addrList;
        this.name = name;
    }

   
    // Property accessors

    public User(int userId) {
		this.id=userId;
	}

	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }

    public List getAddrList() {
        return this.addrList;
    }
    
    public void setAddrList(List addrList) {
        this.addrList = addrList;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}
package com.demo.pojo;



/**
 * AddressList entity. @author MyEclipse Persistence Tools
 */

public class AddrEntity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String tel;
     private String email;
     private int userId;


   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


}
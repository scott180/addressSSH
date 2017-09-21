package com.demo.pojo;



/**
 * AddressList entity. @author MyEclipse Persistence Tools
 */

public class AddressList  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String tel;
     private String email;
     private User user;


    // Constructors

    /** default constructor */
    public AddressList() {
    }

    
    /** full constructor */
    public AddressList(String name, String tel, String email, User user) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.user = user;
    }

   
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

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
   








}
package com.spring.root.user.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;  
    
    private Integer id;  
      
    private String name;  
      
    private String loginName;  
    
    private String password;  
    
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override  
    public String toString() {  
        return "User [id=" + id + ", name=" + name + ", loginName=" + loginName + "]";  
    }  
}

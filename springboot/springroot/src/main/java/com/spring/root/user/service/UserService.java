package com.spring.root.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.root.user.dao.UserDao;
import com.spring.root.user.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void insert(User user){
		userDao.insert(user);
	}
	
	/**
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public String login(String loginName,String password){
		if(loginName==null||"".equals(loginName)||
				password==null||"".equals(password)){
			return "error：loginName and password is not empty";
		}
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		User users = userDao.selectOneByCondition(user);
		if(users!=null){
			return "success...";
		}
		return "error：password is error";
	}
	

}

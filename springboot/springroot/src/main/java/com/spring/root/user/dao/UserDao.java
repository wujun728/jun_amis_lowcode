package com.spring.root.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.root.user.entity.User;

@Mapper
public interface UserDao {
	int insert(User user);
	
	User selectOneByCondition(User user);  
	
	User selectById(Integer id);  
    
    int updateById(User user);  
      
    int deleteById(Integer id);  
      
    List<User> queryAll(); 
}

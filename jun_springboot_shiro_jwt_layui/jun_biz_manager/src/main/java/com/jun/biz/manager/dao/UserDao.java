package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.User;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "u_user")
public interface UserDao extends BaseDao<User,Long> {
	User selectByUsername(String v) ;
	User selectByEmail(String v) ;
	
}

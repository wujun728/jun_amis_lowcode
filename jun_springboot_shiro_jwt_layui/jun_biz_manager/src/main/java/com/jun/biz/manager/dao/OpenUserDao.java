package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.OpenUser;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "u_open_user")
public interface OpenUserDao extends BaseDao<OpenUser,Long> {
	
}

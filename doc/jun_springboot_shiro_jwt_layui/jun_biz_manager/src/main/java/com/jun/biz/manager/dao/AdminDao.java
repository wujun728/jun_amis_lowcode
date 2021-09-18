package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Admin;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "s_admin")
public interface AdminDao extends BaseDao<Admin,Long> {
	Admin selectByUsername(String v) ;
	
}

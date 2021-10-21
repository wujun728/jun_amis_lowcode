package com.jun.biz.manager.dao;


import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.AdminLoginRecord;
import com.jun.biz.manager.model.annotation.MapperMapping;

@MapperMapping(table = "s_admin_login_record")
public interface AdminLoginRecordDao extends BaseDao<AdminLoginRecord,Long> {
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.ConsigneeInfo;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "u_consignee_info")
public interface ConsigneeInfoDao extends BaseDao<ConsigneeInfo,Long> {
	
}

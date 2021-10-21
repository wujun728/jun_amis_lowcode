package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.OrderLog;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "o_order_log")
public interface OrderLogDao extends BaseDao<OrderLog,Long> {
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.GatewayOrder;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "o_gateway_order")
public interface GatewayOrderDao extends BaseDao<GatewayOrder,Long> {
	GatewayOrder selectByOrderId(Integer v) ;
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.OrderItem;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "o_order_item")
public interface OrderItemDao extends BaseDao<OrderItem,Long> {
	
}

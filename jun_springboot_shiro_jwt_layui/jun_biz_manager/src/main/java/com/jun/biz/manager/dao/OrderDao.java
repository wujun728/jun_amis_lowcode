package com.jun.biz.manager.dao;
import org.apache.ibatis.annotations.Param;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Order;
import com.jun.biz.manager.model.annotation.MapperMapping;
import com.jun.biz.manager.model.dao.DateCount;

import java.util.Date;
import java.util.List;


@MapperMapping(table = "o_order")
public interface OrderDao extends BaseDao<Order,Long> {
	Order selectByNo(String v) ;

	Double sumAmount(Integer status);

	List<DateCount> countOrder(@Param("minCreateTime") Date minCreateTime, @Param("maxCreateTime") Date maxCreateTime);

}

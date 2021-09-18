package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.ProductLog;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_product_log")
public interface ProductLogDao extends BaseDao<ProductLog,Long> {
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Product;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_product")
public interface ProductDao extends BaseDao<Product,Long> {
	
}

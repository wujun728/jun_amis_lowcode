package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Cart;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "u_cart")
public interface CartDao extends BaseDao<Cart,Long> {
	Cart selectByUserId(Integer v) ;
	
}

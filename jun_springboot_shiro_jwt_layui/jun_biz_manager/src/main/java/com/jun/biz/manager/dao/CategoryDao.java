package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Category;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_category")
public interface CategoryDao extends BaseDao<Category,Long> {
	
}

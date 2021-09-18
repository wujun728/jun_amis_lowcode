package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Property;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_property")
public interface PropertyDao extends BaseDao<Property,Long> {
	
}

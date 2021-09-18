package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Logistic;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "o_logistic")
public interface LogisticDao extends BaseDao<Logistic,Long> {
	
}

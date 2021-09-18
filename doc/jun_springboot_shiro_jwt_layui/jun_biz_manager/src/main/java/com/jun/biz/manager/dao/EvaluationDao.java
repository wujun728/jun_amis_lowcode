package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Evaluation;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_evaluation")
public interface EvaluationDao extends BaseDao<Evaluation,Long> {
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.RefundApply;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "o_refund_apply")
public interface RefundApplyDao extends BaseDao<RefundApply,Long> {
	
}

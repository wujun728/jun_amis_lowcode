package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.AdSpace;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "a_ad_space")
public interface AdSpaceDao extends BaseDao<AdSpace, Long> {
	AdSpace selectByNo(String v);

}

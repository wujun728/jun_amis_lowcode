package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.AdItem;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "a_ad_item")
public interface AdItemDao extends BaseDao<AdItem, Long> {

    void deleteBySpaceId(Long spaceId);

}

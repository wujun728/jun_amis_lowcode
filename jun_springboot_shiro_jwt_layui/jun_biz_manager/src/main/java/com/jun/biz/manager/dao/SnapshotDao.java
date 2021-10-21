package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Snapshot;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "p_snapshot")
public interface SnapshotDao extends BaseDao<Snapshot,Long> {
	
}

package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Favorite;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "u_favorite")
public interface FavoriteDao extends BaseDao<Favorite,Long> {
	
}

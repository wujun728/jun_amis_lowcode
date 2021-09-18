package com.jun.biz.manager.dao;

import org.apache.ibatis.annotations.Param;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.SessionToken;
import com.jun.biz.manager.model.annotation.MapperMapping;
import com.jun.biz.manager.model.dao.DateCount;

import java.util.Date;
import java.util.List;


@MapperMapping(table = "u_session_token")
public interface SessionTokenDao extends BaseDao<SessionToken,Long> {
	SessionToken selectBySessionToken(String v) ;

	List<DateCount> countToken(@Param("minCreateTime") Date minCreateTime, @Param("maxCreateTime") Date maxCreateTime);
}

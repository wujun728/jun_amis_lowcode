package com.qingshop.mall.modules.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingshop.mall.modules.system.entity.SysConfig;

/**
 * ConfigMapper
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

	/**
	 * 根据key跟新
	 * 
	 * @param key
	 * @param value
	 * @return int
	 */
	int updateByKey(@Param("key") String key, @Param("value") String value);

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return int
	 */
	String getByKey(@Param("key") String key);

}

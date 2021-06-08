package com.qingshop.mall.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingshop.mall.modules.system.entity.SysLog;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

	void deleteAll();

}
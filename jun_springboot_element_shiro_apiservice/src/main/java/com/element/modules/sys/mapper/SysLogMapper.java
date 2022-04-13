package com.element.modules.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
	
}

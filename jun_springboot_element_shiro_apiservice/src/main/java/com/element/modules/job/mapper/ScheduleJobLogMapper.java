package com.element.modules.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 */
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {
	
}

package com.element.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.element.common.utils.PageUtils;
import com.element.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils listJobLogByPage(Map<String, Object> params);
	
}

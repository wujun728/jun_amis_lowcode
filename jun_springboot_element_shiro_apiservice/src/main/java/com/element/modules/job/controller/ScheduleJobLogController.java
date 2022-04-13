package com.element.modules.job.controller;

import com.element.common.utils.PageUtils;
import com.element.common.utils.ResultVo;
import com.element.modules.job.entity.ScheduleJobLogEntity;
import com.element.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务日志
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public ResultVo list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobLogService.listJobLogByPage(params);
		return ResultVo.ok().put("page", page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public ResultVo info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);
		return ResultVo.ok().put("log", log);
	}
}

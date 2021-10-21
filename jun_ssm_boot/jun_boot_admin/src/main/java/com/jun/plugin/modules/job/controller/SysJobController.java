package com.jun.plugin.modules.job.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.plugin.common.exception.TaskException;
import com.jun.plugin.common.util.PageUtil;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.PageResultVo;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.job.model.SysJob;
import com.jun.plugin.modules.job.model.SysJobLog;
import com.jun.plugin.modules.job.service.SysJobLogService;
import com.jun.plugin.modules.job.service.SysJobService;

@Controller
@RequestMapping("/sysjob")
public class SysJobController {

	private static final Logger logger = LoggerFactory.getLogger(SysJobController.class);

	@Autowired
	private SysJobService sysJobService;

	@Autowired
	private SysJobLogService sysJobLogService;

	@PostMapping("/list")
	@ResponseBody
	public PageResultVo pageSysJob(SysJob sysJob, Integer limit, Integer offset) {
		try {
			PageHelper.startPage(PageUtil.getPageNo(limit, offset), limit);
			List<SysJob> jobList = sysJobService.listSysJobs(sysJob);
			PageInfo<SysJob> pages = new PageInfo<>(jobList);
			return ResultUtil.table(jobList, pages.getTotal());
		} catch (Exception e) {
			logger.error(String.format("RoleController.loadRoles%s", e));
			throw e;
		}
	}

	@GetMapping("/add")
	public String add() {
		return "sysjob/add";
	}

	@PostMapping("/addSysJob")
	@ResponseBody
	public ResponseVo<?> addSysJob(SysJob sysJob) throws SchedulerException, TaskException {
		sysJob.setJobId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		Date currentDate = new Date();
		sysJob.setCreateTime(currentDate);
		sysJob.setUpdateTime(currentDate);
		int result = sysJobService.insertJob(sysJob);
		if (result > 0) {
			return ResultUtil.success("添加任务成功");
		} else {
			return ResultUtil.error("添加任务失败");
		}
	}

	@GetMapping("/editSysJob")
	public String detail(Model model, String jobId) {
		SysJob sysJob = sysJobService.getSysJobById(Long.valueOf(jobId));
		model.addAttribute("sysJob", sysJob);
		return "sysjob/detail";
	}

	@PostMapping("/editSysJob")
	@ResponseBody
	public ResponseVo<?> editSysJob(@ModelAttribute("sysJob") SysJob sysJob) throws SchedulerException, TaskException {
		Date currentDate = new Date();
		sysJob.setUpdateTime(currentDate);
		int result = sysJobService.updateJob(sysJob);
		if (result > 0) {
			return ResultUtil.success("编辑任务成功");
		} else {
			return ResultUtil.error("编辑任务失败");
		}
	}

	@PostMapping("/batchDelete")
	@ResponseBody
	public ResponseVo<?> batchDelete(String jobIdStr) throws SchedulerException {
		sysJobService.deleteJobByIds(jobIdStr);
		return ResultUtil.success("删除成功");
	}

	@PostMapping("/deleteSysJob")
	@ResponseBody
	public ResponseVo<?> delete(String jobId) throws SchedulerException {
		sysJobService.deleteJobByIds(jobId);
		return ResultUtil.success("删除成功");
	}

	@PostMapping("/logBatchDelete")
	@ResponseBody
	public ResponseVo<?> logBatchDelete(String jobLogIdStr) throws SchedulerException {
		sysJobLogService.deleteJobLogByIds(jobLogIdStr);
		return ResultUtil.success("删除成功");
	}

	@PostMapping("/changeStatus")
	@ResponseBody
	public ResponseVo<?> changeStatus(SysJob job) throws SchedulerException {
		SysJob newJob = sysJobService.getSysJobById(job.getJobId());
		newJob.setStatus(job.getStatus());
		int result = sysJobService.changeStatus(newJob);
		if (result > 0) {
			return ResultUtil.success("修改成功");
		} else {
			return ResultUtil.error("修改失败");
		}
	}

	@PostMapping("/runSysJob")
	@ResponseBody
	public ResponseVo<?> run(SysJob job) throws SchedulerException {
		sysJobService.run(job);
		return ResultUtil.success("任务运行成功");
	}

	@RequestMapping(value = "/calcRunTime")
	@ResponseBody
	public Object calcRunTime(String expression) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		StringBuffer sbResult = new StringBuffer();
		sbResult.append("[");
		try {
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(expression);
			Date start = trigger.getStartTime();
			for (int i = 0; i < 5; i++) {
				Date next = trigger.getFireTimeAfter(start);
				sbResult.append(", \"").append(sdf.format(next)).append("\"");
				start = next;
			}
			sbResult.append("]");
		} catch (ParseException e) {
			sbResult.append("cron表达式错误！");
			sbResult.append("]");
			return sbResult;
		}
		return sbResult.replace(sbResult.indexOf(","), sbResult.indexOf(",") + 1, "");
	}

	@PostMapping("/jobloglist")
	@ResponseBody
	public PageResultVo pageJobLoglist(SysJobLog sysJobLog, Integer limit, Integer offset) {
		try {
			PageHelper.startPage(PageUtil.getPageNo(limit, offset), limit);
			List<SysJobLog> jobLogList = sysJobLogService.listSysLogJobs(sysJobLog);
			PageInfo<SysJobLog> pages = new PageInfo<>(jobLogList);
			return ResultUtil.table(jobLogList, pages.getTotal());
		} catch (Exception e) {
			logger.error(String.format("RoleController.loadRoles%s", e));
			throw e;
		}
	}

	@GetMapping("/jobloginfo")
	public String jobloginfo(Model model, String jobLogId) {
		SysJobLog sysJobLog = sysJobLogService.getSysJobLogById(Long.valueOf(jobLogId));
		model.addAttribute("sysJobLog", sysJobLog);
		return "sysjob/logdetail";
	}

}

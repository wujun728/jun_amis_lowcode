package com.ruoyi.project.monitor.job.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.monitor.job.util.CronUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.service.JobService;

/**
 * 调度任务信息操作处理
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/job")
public class JobController extends BaseController {
    private String prefix = "monitor/job";

	@Autowired
	private JobService jobService;

	@RequiresPermissions("monitor:job:view")
	@GetMapping()
	public String job() {
		return prefix + "/job";
	}

	@RequiresPermissions("monitor:job:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HttpServletRequest request) {
		return jobService.selectJobList(request, true);
	}

	@Log(title = "定时任务", businessType = BusinessType.EXPORT)
	@RequiresPermissions("monitor:job:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HttpServletRequest request) {
    	//定义表头
    	Map<String, String> headers = new LinkedHashMap<String, String>();
    	headers.put("job_id", "任务序号");
    	headers.put("job_name", "任务名称");
    	headers.put("job_group", "任务组名");
    	headers.put("invoke_target", "调用目标字符串");
    	headers.put("cron_expression", "执行表达式");
    	headers.put("misfire_policy", "计划策略");
    	headers.put("concurrent", "并发执行");
    	headers.put("status", "任务状态");
    	headers.put("create_time", "创建时间");
    	headers.put("remark", "备注");

		//数据集合
		List<?> dataList = jobService.selectJobList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "定时任务");
	}

	@Log(title = "定时任务", businessType = BusinessType.DELETE)
	@RequiresPermissions("monitor:job:remove")
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) throws SchedulerException {
		jobService.deleteJobByIds(ids);
		return success();
	}

	@RequiresPermissions("monitor:job:detail")
	@GetMapping("/detail/{job_id}")
	public String detail(@PathVariable("job_id") String job_id, ModelMap mmap) {
		mmap.put("name", "job");
		mmap.put("job", jobService.selectJobById(job_id));
		return prefix + "/detail";
	}

	/**
	 * 任务调度状态修改
	 */
	@Log(title = "定时任务", businessType = BusinessType.UPDATE)
	@RequiresPermissions("monitor:job:changeStatus")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(HttpServletRequest request) throws SchedulerException {
		return toAjax(jobService.changeStatus(request));
	}

	/**
	 * 任务调度立即执行一次
	 */
	@Log(title = "定时任务", businessType = BusinessType.RUNJOB)
	@RequiresPermissions("monitor:job:changeStatus")
	@PostMapping("/run")
	@ResponseBody
	public AjaxResult run(HttpServletRequest request) throws SchedulerException {
    	String job_id = RequestUtil.getValue(request, "job_id");
		jobService.run(job_id);
		return success();
	}

	/**
	 * 新增调度
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存调度
	 */
	@Log(title = "定时任务", businessType = BusinessType.INSERT)
	@RequiresPermissions("monitor:job:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request) throws SchedulerException, TaskException {
        String cron_expression = RequestUtil.getValue(request, "cron_expression");
        if (!CronUtils.isValid(cron_expression)) {
            return AjaxResult.error("cron表达式不正确");
        }
		return toAjax(jobService.insertJob(request));
	}

	/**
	 * 修改调度
	 */
	@GetMapping("/edit/{job_id}")
	public String edit(@PathVariable("job_id") String job_id, ModelMap mmap) {
		mmap.put("job", jobService.selectJobById(job_id));
		return prefix + "/edit";
	}

	/**
	 * 修改保存调度
	 */
	@Log(title = "定时任务", businessType = BusinessType.UPDATE)
	@RequiresPermissions("monitor:job:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HttpServletRequest request) throws SchedulerException, TaskException {
        String cron_expression = RequestUtil.getValue(request, "cron_expression");
        if (!CronUtils.isValid(cron_expression)) {
            return AjaxResult.error("cron表达式不正确");
        }
		return toAjax(jobService.updateJob(request));
	}

	/**
	 * 校验cron表达式是否有效
	 */
	@PostMapping("/checkCronExpressionIsValid")
	@ResponseBody
	public boolean checkCronExpressionIsValid(HttpServletRequest request) {
    	String cron_expression = RequestUtil.getValue(request, "cron_expression");
		return jobService.checkCronExpressionIsValid(cron_expression);
	}
}
package com.ruoyi.project.monitor.job.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.service.JobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.job.service.JobLogService;

/**
 * 调度日志操作处理
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class JobLogController extends BaseController {
    private String prefix = "monitor/job";

    @Autowired
    private JobService jobService;

    @Autowired
    private JobLogService jobLogService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
	public String jobLog() {
		return prefix + "/jobLog";
	}

    public String jobLog(@RequestParam(value = "jobId", required = false) String jobId, ModelMap mmap) {
        if (StrUtil.isNotBlank(jobId)) {
            Job job = jobService.selectJobById(jobId);
            mmap.put("job", job);
        }
        return prefix + "/jobLog";
    }

	@RequiresPermissions("monitor:job:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HttpServletRequest request) {
		return jobLogService.selectJobLogList(request, true);
	}

	@Log(title = "调度日志", businessType = BusinessType.EXPORT)
	@RequiresPermissions("monitor:job:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HttpServletRequest request) {
    	//定义表头
    	Map<String, String> headers = new LinkedHashMap<String, String>();
    	headers.put("job_log_id", "日志序号");
    	headers.put("job_name", "任务名称");
    	headers.put("job_group", "任务组名");
    	headers.put("invoke_target", "调用目标字符串");
    	headers.put("job_message", "日志信息");
    	headers.put("status", "执行状态");
    	headers.put("exception_info", "异常信息");
    	headers.put("create_time", "创建时间");

		//数据集合
		List<?> dataList = jobLogService.selectJobLogList(request, false).getRows();
		return ExcelUtilx.exportExcel(headers, dataList, "调度日志");
	}

	@Log(title = "调度日志", businessType = BusinessType.DELETE)
	@RequiresPermissions("monitor:job:remove")
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(jobLogService.deleteJobLogByIds(ids));
	}

	@RequiresPermissions("monitor:job:detail")
	@GetMapping("/detail/{job_log_id}")
	public String detail(@PathVariable("job_log_id") String job_log_id, ModelMap mmap) {
		mmap.put("name", "jobLog");
		mmap.put("jobLog", jobLogService.selectJobLogById(job_log_id));
		return prefix + "/detail";
	}

	@Log(title = "调度日志", businessType = BusinessType.CLEAN)
	@RequiresPermissions("monitor:job:remove")
	@PostMapping("/clean")
	@ResponseBody
	public AjaxResult clean() {
		jobLogService.cleanJobLog();
		return success();
	}
}
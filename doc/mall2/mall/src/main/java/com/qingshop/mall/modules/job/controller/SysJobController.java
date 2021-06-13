package com.qingshop.mall.modules.job.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.exception.TaskException;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.job.entity.SysJob;
import com.qingshop.mall.modules.job.service.ISysJobService;

/**
 * 定时任务调度表 前端控制器
 *
 * @since 2019-10-16
 */
@Controller
@RequestMapping("/system/sysJob")
public class SysJobController extends BaseController {

	@Autowired
	private ISysJobService sysJobService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listJob")
	@RequestMapping("/list")
	public String list() {
		return "system/job/list";
	}

	/**
	 * 分页查询任务
	 */
	@RequiresPermissions("listJob")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysJob> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysJob> ew = new QueryWrapper<SysJob>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("job_name", search).or().like("job_group", search);
		}
		ew.orderByDesc("createTime");
		IPage<SysJob> pageData = sysJobService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 删除任务
	 * 
	 * @throws SchedulerException
	 */
	@RequiresPermissions("deleteJob")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(String id) throws SchedulerException {
		sysJobService.deleteJobByIds(id);
		return Rest.ok();
	}

	/**
	 * 增加任务
	 */
	@RequiresPermissions("addJob")
	@RequestMapping("/add")
	public String addJob() {
		return "system/job/add";
	}

	/**
	 * 执行新增
	 * 
	 * @throws TaskException
	 * @throws SchedulerException
	 */
	@RequiresPermissions("addJob")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysJob sysJob) throws SchedulerException, TaskException {
		sysJob.setJobId(DistributedIdWorker.nextId());
		Date currentDate = new Date();
		sysJob.setCreateTime(currentDate);
		sysJob.setUpdateTime(currentDate);
		sysJobService.insertJob(sysJob);
		return Rest.ok();
	}

	/**
	 * 编辑任务
	 */
	@RequiresPermissions("editJob")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		SysJob sysJob = sysJobService.getById(id);
		model.addAttribute("sysJob", sysJob);
		return "system/job/edit";
	}

	/**
	 * 执行编辑
	 * 
	 * @throws TaskException
	 * @throws SchedulerException
	 */
	@RequiresPermissions("editJob")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysJob sysJob) throws SchedulerException, TaskException {
		Date currentDate = new Date();
		sysJob.setUpdateTime(currentDate);
		sysJobService.updateJob(sysJob);
		return Rest.ok();
	}

	/**
	 * 任务调度状态修改
	 */
	@RequiresPermissions("editJob")
	@RequestMapping("/changeStatus")
	@ResponseBody
	public Rest changeStatus(SysJob job) throws SchedulerException {
		SysJob newJob = sysJobService.getById(job.getJobId());
		newJob.setStatus(job.getStatus());
		return Rest.okData(sysJobService.changeStatus(newJob));
	}

	/**
	 * 任务调度立即执行一次
	 */
	@RequiresPermissions("editJob")
	@RequestMapping("/run")
	@ResponseBody
	public Rest run(SysJob job) throws SchedulerException {
		sysJobService.run(job);
		return Rest.ok();
	}

	/**
	 * 校验cron表达式是否有效
	 */
	@RequestMapping("/checkCronExpressionIsValid")
	@ResponseBody
	public Rest checkCronExpressionIsValid(SysJob job) {
		if (sysJobService.checkCronExpressionIsValid(job.getCronExpression())) {
			return Rest.ok();
		}
		return Rest.failure("cron表达式错误！");
	}
}

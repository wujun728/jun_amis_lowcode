package com.qingshop.mall.modules.job.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.job.entity.SysJobLog;
import com.qingshop.mall.modules.job.service.ISysJobLogService;

/**
 * 定时任务调度日志表 前端控制器
 */
@Controller
@RequestMapping("/system/sysJobLog")
public class SysJobLogController extends BaseController {

	@Autowired
	private ISysJobLogService sysJobLogService;

	/**
	 *列表页
	 */
	@RequiresPermissions("listJob")
	@RequestMapping("/list")
	public String list() {
		return "system/joblog/list";
	}

	/**
	 * 分页查询日志
	 */
	@RequiresPermissions("listJob")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest list(String search, String daterange, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysJobLog> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysJobLog> ew = new QueryWrapper<SysJobLog>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("job_name", search);
		}
		// 日期查询
		if (StringUtils.isNotBlank(daterange)) {
			String[] dateranges = StringUtils.split(daterange, "-");
			ew.between("create_time", dateranges[0].trim().replaceAll("/", "-") + " 00:00:00", dateranges[1].trim().replaceAll("/", "-") + " 23:59:59");
		}
		ew.orderByDesc("create_time");
		IPage<SysJobLog> pageData = sysJobLogService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 日志详情
	 */
	@RequiresPermissions("listJob")
	@RequestMapping("/detail/{id}")
	public String edit(@PathVariable Long id, ModelMap map) {
		SysJobLog sysJobLog = sysJobLogService.getById(id);
		map.put("sysJobLog", sysJobLog);
		return "system/joblog/detail";
	}

	@RequiresPermissions("deleteJobLog")
	@PostMapping("/deleteAll")
	@ResponseBody
	public Rest deleteAll() {
		sysJobLogService.cleanJobLog();
		return Rest.ok();
	}

}
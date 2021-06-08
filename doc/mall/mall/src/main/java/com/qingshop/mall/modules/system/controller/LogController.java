package com.qingshop.mall.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysLog;
import com.qingshop.mall.modules.system.service.ISysLogService;

/**
 * 日志控制器
 */
@Controller
@RequestMapping("/system/log")
public class LogController extends BaseController {

	@Autowired
	private ISysLogService sysLogService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listLog")
	@RequestMapping("/list")
	public String list() {
		return "system/log/list";
	}

	/**
	 * 分页查询日志
	 */
	@RequiresPermissions("listLog")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, String daterange, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysLog> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysLog> ew = new QueryWrapper<SysLog>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("user_name", search).or().like("title", search);
		}
		// 日期查询
		if (StringUtils.isNotBlank(daterange)) {
			String[] dateranges = StringUtils.split(daterange, "-");
			ew.between("create_time", dateranges[0].trim().replaceAll("/", "-") + " 00:00:00", dateranges[1].trim().replaceAll("/", "-") + " 23:59:59");
		}
		ew.orderByDesc("createTime");
		IPage<SysLog> pageData = sysLogService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 获取参数
	 */
	@RequiresPermissions("listLog")
	@RequestMapping("/params/{id}")
	@ResponseBody
	public Rest params(@PathVariable Long id) {
		SysLog sysLog = sysLogService.getById(id);
		return Rest.okData(sysLog.getParams());
	}

	/**
	 * 清空日志
	 */
	@RequiresPermissions("deleteLog")
	@PostMapping("/deleteAll")
	@ResponseBody
	public Rest deleteAll() {
		sysLogService.deleteAll();
		return Rest.ok();
	}

}

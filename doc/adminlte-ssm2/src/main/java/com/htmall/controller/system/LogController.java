package com.htmall.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htmall.common.controller.BaseController;
import com.htmall.entity.SysLog;
import com.htmall.service.ISysLogService;

@Controller
@RequestMapping("/system/log")
public class LogController extends BaseController {

	@Autowired
	private ISysLogService sysLogService;

	/**
	 * 分页查询日志
	 */
	@RequiresPermissions("listLog")
	@RequestMapping("/list/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "15") Integer pageSize, String search, String daterange, Model model) {

		Page<SysLog> page = getPage(pageNumber, pageSize);
		// 查询分页
		QueryWrapper<SysLog> ew = new QueryWrapper<SysLog>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("user_name", search);
			ew.like("title", search);
			model.addAttribute("search", search);
		}
		// 日期查询
		if (StringUtils.isNotBlank(daterange)) {
			model.addAttribute("daterange", daterange);
			String[] dateranges = StringUtils.split(daterange, "-");
			ew.between("create_time", dateranges[0].trim().replaceAll("/", "-") + " 00:00:00", dateranges[1].trim().replaceAll("/", "-") + " 23:59:59");
		}
		ew.orderByAsc("create_time");
		IPage<SysLog> pageData = sysLogService.page(page, ew);
		model.addAttribute("pageSize", pageSize);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("records", pageData.getRecords());
		resultMap.put("currentPage", pageData.getCurrent());
		resultMap.put("sizePage", pageData.getSize());
		resultMap.put("totalPage", pageData.getPages());
		resultMap.put("total", pageData.getTotal());
		model.addAttribute("pageData", resultMap);
		return "system/log/list";
	}

	/**
	 * 获取参数
	 */
	@RequestMapping("/params/{id}")
	@ResponseBody
	public String params(@PathVariable String id, Model model) {
		SysLog sysLog = sysLogService.getById(id);
		return sysLog.getParams();
	}

}

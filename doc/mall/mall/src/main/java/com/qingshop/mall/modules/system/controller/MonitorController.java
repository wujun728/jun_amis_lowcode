package com.qingshop.mall.modules.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingshop.mall.modules.common.BaseController;

/**
 * 监控
 */
@Controller
@RequestMapping("/system/monitor")
public class MonitorController extends BaseController {

	/**
	 * 系统监控列表页
	 */
	@RequiresPermissions("listMonitor")
	@RequestMapping("/list")
	public String list() {
		return redirectTo("/monitor/druid/");
	}
}

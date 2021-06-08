package com.qingshop.mall.modules.wxapi.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingshop.mall.modules.common.BaseController;

/**
 * 监控
 */
@Controller
@RequestMapping("/wx/api")
public class WxApiController extends BaseController {

	/**
	 * 系统监控列表页
	 */
	@RequiresPermissions("listWxApi")
	@RequestMapping("/list")
	public String list() {
		return redirectTo("/swagger-ui.html");
	}
}

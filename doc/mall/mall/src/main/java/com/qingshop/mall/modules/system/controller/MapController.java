package com.qingshop.mall.modules.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingshop.mall.modules.common.BaseController;

/**
 * 地图控制器
 */
@Controller
@RequestMapping("/system/map")
public class MapController extends BaseController{
	
	/**
	 * 列表页
	 */
	@RequiresPermissions("listMap")
	@RequestMapping("/list")
	public String list() {
		return "system/map/mapList";
	}
	
	

}

package com.qingshop.mall.modules.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.constant.Constants;
import com.qingshop.mall.modules.system.service.ISysConfigService;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

@Controller
@RequestMapping("/system/config")
public class ConfigController {

	@Autowired
	private ISysConfigService sysConfigService;

	@PostMapping(value = "/saveStorage")
	@ResponseBody
	public Rest saveConfig(ConfigStorageVo config) {
		config.setSetFlag(Constants.STATUS_VALID);
		sysConfigService.saveStorageConfig(config);
		return Rest.ok("存储设置成功！");
	}

}

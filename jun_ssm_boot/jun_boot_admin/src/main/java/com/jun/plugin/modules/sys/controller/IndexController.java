package com.jun.plugin.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/users")
	public String userList() {
		return "user/list";
	}

	@GetMapping("/roles")
	public String roleList() {
		return "role/list";
	}

	@GetMapping("/menus")
	public String menuList() {
		return "menu/list";
	}

	@GetMapping(value = "/icons")
	public String getIcons() {
		return "icon/icons";
	}

	@GetMapping("/sysJob")
	public String sysjobList() {
		return "sysjob/list";
	}

	@GetMapping("/sysJobLog")
	public String sysjobLogList() {
		return "sysjob/loglist";
	}

	@GetMapping(value = "/toicon")
	public String toicon() {
		return "menu/icon";
	}

	@GetMapping(value = "/tocron")
	public String tocron() {
		return "sysjob/cron";
	}

	@GetMapping(value = "/sysfile")
	public String toFiles() {
		return "sysfile/list";
	}

	// 切换主题
	@GetMapping("/switchSkin")
	public String switchSkin(ModelMap mmap) {
		return "skin";
	}
}

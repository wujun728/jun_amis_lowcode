package com.ifast.sys.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ifast.business.oss.domain.FileDO;
import com.ifast.business.oss.service.FileService;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.base.domain.Tree;
import com.ifast.sys.base.domain.MenuDO;
import com.ifast.sys.base.service.MenuService;

@Controller
public class IndexController extends AdminBaseController {
	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/login";
	}

	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		model.addAttribute("username", getUser().getUsername());
		// FileDO fileDO = fileService.selectById(getUser().getPicId());
		FileDO fileDO = fileService.getById(getUser().getPicId());
		model.addAttribute("picUrl", fileDO == null ? "/img/photo_s.jpg" : fileDO.getUrl());
		return "index_v1";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}
}

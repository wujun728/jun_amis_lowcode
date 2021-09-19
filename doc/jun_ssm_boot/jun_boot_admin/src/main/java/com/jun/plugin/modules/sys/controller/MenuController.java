package com.jun.plugin.modules.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	/** 1:全部资源，2：菜单资源 */
	private static final String[] MENU_FLAG = { "1", "2" };

	@Autowired
	private MenuService menuService;

	/* 菜单列表数据 */
	@PostMapping("/list")
	@ResponseBody
	public List<SysMenu> loadMenus(String flag) {
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		if (StringUtils.isBlank(flag) || MENU_FLAG[0].equals(flag)) {
			menuList = menuService.listAllPermsByStatus(CoreConst.STATUS_VALID);
		} else if (MENU_FLAG[1].equals(flag)) {
			menuList = menuService.listAllMenuName(CoreConst.STATUS_VALID);
		}
		return menuList;
	}

	/* 菜单详情 */
	@GetMapping("/add")
	public String add(Model model, String menuId) {
		SysMenu menu = new SysMenu();
		Integer checkedType = 0;
		if ("0".equals(menuId)) {
			menu.setName("顶层菜单");
			menu.setMenuId(0L);
			menu.setType(0);
		} else {
			menu = menuService.getSysMenuByMenuId(Long.valueOf(menuId));
			Integer type = menu.getType();
			checkedType = type;
			if (type.equals(0)) {
				menu.setType(1);
			}
			if (type.equals(1)) {
				menu.setType(2);
			}
		}
		model.addAttribute("checkedType", checkedType);
		model.addAttribute("menu", menu);
		return "menu/add";
	}

	/* 添加菜单 */
	@ResponseBody
	@PostMapping("/doAdd")
	public ResponseVo<?> addMenu(SysMenu menu) {
		try {
			int a = menuService.save(menu);
			if (a > 0) {
				return ResultUtil.success("添加菜单成功");
			} else {
				return ResultUtil.error("添加菜单失败");
			}
		} catch (Exception e) {
			logger.error(String.format("MenuController.addMenu%s", e));
			throw e;
		}
	}

	/* 删除菜单 */
	@ResponseBody
	@PostMapping("/delete")
	public ResponseVo<?> deleteMenu(String menuId) {
		try {
			int subPermsCount = menuService.countMenuByParentId(Long.valueOf(menuId));
			if (subPermsCount > 0) {
				return ResultUtil.error("存在子菜单,不允许删除");
			}
			int menuAuthedCount = menuService.countRoleMenuByMenuId(Long.valueOf(menuId));
			if (menuAuthedCount > 0) {
				return ResultUtil.error("菜单已分配,不允许删除");
			}
			int deleteResult = menuService.deleteMenuByMenuId(Long.valueOf(menuId));
			if (deleteResult > 0) {
				return ResultUtil.success("删除菜单成功");
			} else {
				return ResultUtil.error("删除菜单失败");
			}
		} catch (Exception e) {
			logger.error(String.format("MenuController.deleteMenu%s", e));
			throw e;
		}
	}

	/* 菜单详情 */
	@GetMapping("/edit")
	public String detail(Model model, String menuId) {
		SysMenu menu = menuService.getSysMenuByMenuId(Long.valueOf(menuId));
		if (null != menu) {
			if (menu.getParentId() == CoreConst.TOP_MENU_ID) {
				model.addAttribute("parentName", CoreConst.TOP_MENU_NAME);
				model.addAttribute("checkType", 0);
			} else {
				SysMenu parent = menuService.getSysMenuByMenuId(menu.getParentId());
				model.addAttribute("parentName", parent.getName());
				model.addAttribute("checkType", parent.getType());
			}
		}
		model.addAttribute("menu", menu);
		return "menu/detail";
	}

	/* 编辑菜单 */
	@ResponseBody
	@PostMapping("/edit")
	public ResponseVo<?> editMenu(@ModelAttribute("menu") SysMenu menu) {
		int updateResult = menuService.updateSysMenuByMenuId(menu);
		if (updateResult > 0) {
			return ResultUtil.success("编辑菜单成功");
		} else {
			return ResultUtil.error("编辑菜单失败");
		}
	}
}

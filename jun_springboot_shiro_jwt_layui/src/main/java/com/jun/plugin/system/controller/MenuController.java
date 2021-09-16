package com.jun.plugin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.service.MenuService;
import com.jun.plugin.system.vo.MenuVo;

import java.util.List;

/**
 * 
 * 
 * ClassName: MenuController create: 2020-04-17 19:07
 *
 * @author: Wujun @since： JDK1.8
 **/
@RestController
@RequestMapping("api/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 查询菜单和权限方法
	 *
	 * @param menuVo
	 * @return
	 */
	@RequestMapping("loadAllMenu")
	public Object loadAllMenu(MenuVo menuVo) {
		return this.menuService.queryAllMenu(menuVo);
	}

	/**
	 * 只查询菜单方法
	 *
	 * @param menuVo
	 * @return
	 */
	@RequestMapping("loadMenu")
	public Object loadMenu(MenuVo menuVo) {
		// 不要权限
		List<Menu> menus = this.menuService.queryAllMenuForList();
		return new DataGridView(Long.valueOf(menus.size()), menus);
	}

	/**
	 * 查询菜单和权限最大的排序码
	 */
	@GetMapping("queryMenuMaxOrderNum")
	public Object queryMenuMaxOrderNum() {
		Integer maxValue = this.menuService.queryMenuMaxOrderNum();
		return new DataGridView(maxValue + 1);
	}

	/**
	 * 添加菜单和权限
	 */
	@PostMapping("addMenu")
	public ResultObj addMenu(Menu menu) {
		try {
			if (menu.getType().equals("topmenu")) {
				menu.setPid(0);
			}
			menu.setSpread(Constant.SPREAD_FALSE);
			menu.setAvailable(Constant.AVAILABLE_TRUE);

			if (null != menu.getIcon()) {
				menu.setIcon("fa " + menu.getIcon());
			}

			this.menuService.saveMenu(menu);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改菜单和权限
	 */
	@PostMapping("updateMenu")
	public ResultObj updateMenu(Menu menu) {
		try {
			if (null != menu.getIcon()) {
				menu.setIcon("fa " + menu.getIcon());
			}
			this.menuService.updateMenu(menu);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 根据id获取菜单
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("getMenuById")
	public Object getMenuById(Integer id) {
		return new DataGridView(this.menuService.getById(id));
	}

	/**
	 * 根据ID查询当前菜单和权限的子菜单和权限的个数 用于前端判断是是否有子部门
	 */
	@GetMapping("getMenuChildrenCountById")
	public Object getMenuChildrenCountById(Integer id) {
		Integer count = this.menuService.queryMenuChildrenCountById(id);
		return new DataGridView(count);
	}

	/**
	 * 删除
	 */
	@RequestMapping("deleteMenu")
	public ResultObj deleteMenu(Integer id) {
		try {
			this.menuService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

}

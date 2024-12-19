package com.ruoyi.project.system.menu.controller;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.AuthorizationUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.MenuService;
import com.ruoyi.project.system.role.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 菜单信息
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	private String prefix = "system/menu";

	@RequiresPermissions("system:menu:view")
	@GetMapping()
	public String menu() {
		return prefix + "/menu";
	}

	@RequiresPermissions("system:menu:list")
	@PostMapping("/list")
	@ResponseBody
	public List<Map<String, Object>> list(HttpServletRequest request) {
		return menuService.selectMenuList(request);
	}

	/**
	 * 删除菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.DELETE)
	@RequiresPermissions("system:menu:remove")
	@GetMapping("/remove/{menu_id}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("menu_id") Long menu_id) {
		if (menuService.selectCountMenuByParentId(menu_id) > 0) {
			return AjaxResult.warn("存在子菜单,不允许删除");
		}
//		if (menuService.selectCountRoleMenuByMenuId(menu_id) > 0) {
//			return AjaxResult.warn("菜单已分配,不允许删除");
//		}
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
		return toAjax(menuService.deleteMenuById(menu_id));
	}

	/**
	 * 新增
	 */
	@GetMapping("/add/{parent_id}")
	public String add(@PathVariable("parent_id") Long parent_id, ModelMap mmap) {
		Menu menu = null;
		if (0L != parent_id) {
			menu = menuService.selectMenuById(parent_id);
		} else {
			menu = new Menu();
			menu.setMenuId(0L);
			menu.setMenuName("主目录");
		}
		mmap.put("menu", menu);
		return prefix + "/add";
	}

	/**
	 * 新增保存菜单
	 */
	@RequiresPermissions("system:menu:add")
	@Log(title = "菜单管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request) {
    	String menu_name = RequestUtil.getValue(request, "menu_name");
    	if(menuService.checkMenuNameUnique(request) > 0) {
            return error("新增菜单【" + menu_name + "】失败，菜单名称已存在！");
    	}
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
		return toAjax(menuService.insertMenu(request));

	}

	/**
	 * 修改菜单
	 */
	@GetMapping("/edit/{menu_id}")
	public String edit(@PathVariable("menu_id") Long menu_id, ModelMap mmap) {
		mmap.put("menu", menuService.selectMenuById(menu_id));
		mmap.put("permitList", menuService.selectPermitsByMenuId(menu_id));
		return prefix + "/edit";
	}

    /**
     * 保存菜单配置
     */
    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HttpServletRequest request) {
    	String menu_name = RequestUtil.getValue(request, "menu_name");
    	if(menuService.checkMenuNameUnique(request) > 0) {
    		return error("新增菜单【" + menu_name + "】失败，菜单名称已存在！");
    	}
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
    	return toAjax(menuService.updateMenu(request));
    }

	/**
	 * 选择菜单图标
	 */
	@GetMapping("/icon")
	public String icon() {
		return prefix + "/icon";
	}

	/**
	 * 校验菜单名称
	 */
	@PostMapping("/checkMenuNameUnique")
	@ResponseBody
	public int checkMenuNameUnique(HttpServletRequest request) {
		return menuService.checkMenuNameUnique(request);
	}

	/**
	 * 加载角色菜单列表树
	 */
	@GetMapping("/roleMenuTreeData")
	@ResponseBody
	public List<Ztree> roleMenuTreeData(String role_id) {
		return menuService.roleMenuTreeData(role_id);
	}

	/**
	 * 加载所有菜单列表树
	 */
	@GetMapping("/menuTreeData")
	@ResponseBody
	public List<Ztree> menuTreeData() {
		return menuService.menuTreeData();
	}

	/**
	 * 选择菜单树
	 */
	@GetMapping("/selectMenuTree/{menu_id}")
	public String selectMenuTree(@PathVariable("menu_id") Long menu_id, ModelMap mmap) {
		mmap.put("menu", menuService.selectMenuById(menu_id));
		return prefix + "/tree";
	}

	/**
	 * 菜单授权角色
	 */
	@GetMapping("/authRole")
	public String authRole(ModelMap mmap) {
		mmap.put("roles", roleService.selectRoleAll());
		return prefix + "/authRole";
	}

    /**
     * 菜单授权角色
     */
    @RequiresPermissions("system:menu:authrole")
    @Log(title = "菜单管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole")
    @ResponseBody
    public AjaxResult authRoleSave(HttpServletRequest request) {
    	return toAjax(menuService.insertRoleMenu(request));
    }

	/**
	 * 加载角色
	 */
	@GetMapping("/loadRoles")
	public String loadRoles(HttpServletRequest request, ModelMap mmap) {
    	String menuIds = RequestUtil.getValue(request, "menuIds");
		mmap.put("roles", menuService.selectRolesByMenuIds(menuIds));
		return prefix + "/roles";
	}
}
package com.qingshop.mall.modules.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.annotation.Log;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysMenu;
import com.qingshop.mall.modules.system.service.ISysMenuService;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

	/**
	 * 菜单服务
	 */
	@Autowired
	private ISysMenuService sysMenuService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listMenu")
	@RequestMapping("/list")
	public String list() {
		return "system/menu/list";
	}

	/**
	 * 分页查询菜单
	 */
	@RequiresPermissions("listMenu")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search) {
		Rest resultMap = new Rest();
		// 查询分页
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.orderByAsc("code");
		if (StringUtils.isNotBlank(search)) {
			ew.like("menu_name", search);
		}
		List<SysMenu> list = sysMenuService.list(ew);
		List<SysMenu> treeList = sysMenuService.getTreeData(list, 0);
		resultMap.put("iTotalDisplayRecords", list.size());
		resultMap.put("iTotalRecords", list.size());
		resultMap.put("aaData", treeList);
		return resultMap;
	}

	/**
	 * 增加菜单
	 */
	@RequiresPermissions("addMenu")
	@RequestMapping("/add")
	public String add(Model model) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.orderByAsc("code");
		ew.eq("pid", "0");
		List<SysMenu> list = sysMenuService.list(ew);
		int deepCode = sysMenuService.count(new QueryWrapper<SysMenu>().eq("deep", '1')) + 1;
		model.addAttribute("list", list);
		model.addAttribute("deepCode", "0" + deepCode);
		model.addAttribute("deepOrder", deepCode);
		return "system/menu/add";

	}

	/**
	 * 添加目录
	 */
	@RequiresPermissions("addMenu")
	@Log("创建目录菜单")
	@RequestMapping("/doAddDir")
	@ResponseBody
	public Rest doAddDir(SysMenu sysMenu) {
		sysMenu.setMenuId(DistributedIdWorker.nextId());
		sysMenu.setPid(0L);
		sysMenu.setDeep(1);
		sysMenu.setUrl("#");
		if (StringUtils.isEmpty(sysMenu.getIcon())) {
			sysMenu.setIcon("fa fa-circle-o");
		}
		sysMenuService.save(sysMenu);
		return Rest.ok();
	}

	/**
	 * 添加菜单
	 */
	@RequiresPermissions("addMenu")
	@Log("创建菜单")
	@RequestMapping("/doAddMenu")
	@ResponseBody
	public Rest doAddMenu(SysMenu sysMenu) {
		sysMenu.setMenuId(DistributedIdWorker.nextId());
		sysMenu.setDeep(2);
		if (StringUtils.isEmpty(sysMenu.getIcon())) {
			sysMenu.setIcon("fa fa-circle-o");
		}
		sysMenuService.save(sysMenu);
		return Rest.ok();
	}

	@RequiresPermissions("addMenu")
	@Log("新增功能菜单")
	@RequestMapping("/doAddAction")
	@ResponseBody
	public Rest doAddAction(SysMenu sysMenu) {
		sysMenu.setMenuId(DistributedIdWorker.nextId());
		sysMenu.setDeep(3);
		sysMenu.setUrl("#");
		sysMenuService.save(sysMenu);
		return Rest.ok();
	}

	/**
	 * 编辑菜单
	 */
	@RequiresPermissions("editMenu")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		SysMenu sysMenu = sysMenuService.getById(id);
		model.addAttribute("sysMenu", sysMenu);
		if (sysMenu.getDeep() == 1) {
			return "/system/menu/edit";
		} else if (sysMenu.getDeep() == 2) {
			QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
			ew.orderByAsc("code");
			ew.eq("pid", "0");
			List<SysMenu> list = sysMenuService.list(ew);
			model.addAttribute("list", list);
			return "/system/menu/editMenu";
		} else {
			QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
			ew.orderByAsc("code");
			ew.eq("pid", "0");
			List<SysMenu> list = sysMenuService.list(ew);
			model.addAttribute("list", list);
			model.addAttribute("pSysMenu", sysMenuService.getById(sysMenu.getPid()));
			return "/system/menu/editAction";
		}
	}

	/**
	 * 执行编辑菜单
	 */
	@RequiresPermissions("editMenu")
	@Log("编辑菜单")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysMenu sysMenu) {
		sysMenuService.updateById(sysMenu);
		return Rest.ok();
	}

	/**
	 * 执行编辑菜单
	 */
	@RequiresPermissions("deleteMenu")
	@Log("删除菜单")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.eq("pid", id);
		List<SysMenu> list = sysMenuService.list(ew);
		if (list.size() > 0) {
			return Rest.failure("该资源存在下级资源，无法删除！");
		} else {
			sysMenuService.removeById(id);
			return Rest.ok();
		}
	}

	/**
	 * 根据父节点获取子菜单
	 */
	@RequestMapping("/json")
	@ResponseBody
	public Rest json(Long pid) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.orderByAsc("sort");
		ew.eq("pid", pid);
		List<SysMenu> list = sysMenuService.list(ew);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (SysMenu m : list) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", m.getMenuId());
			map.put("text", StringUtils.join(m.getCode(), "-", m.getMenuName()));
			listMap.add(map);
		}
		return Rest.okData(listMap);
	}

	/**
	 * 根据父节点查询子节点的数量
	 */
	@RequestMapping("/menuCount")
	@ResponseBody
	public Rest menuCount(Long pid) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.orderByAsc("sort");
		ew.eq("pid", pid);
		int memuCount = sysMenuService.count(ew) + 1;
		return Rest.okData(memuCount);
	}

	/**
	 * 验证菜单资源名称是否存在
	 */
	@RequestMapping("/checkMenuResource")
	@ResponseBody
	public Rest checkMenuResource(String resource) {
		List<SysMenu> list = sysMenuService.list(new QueryWrapper<SysMenu>().eq("resource", resource));
		if (list.size() > 0) {
			return Rest.failure("资源已存在,请换一个尝试.");
		}
		return Rest.ok();
	}

}

package com.zhonghe.active4j.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.service.SysDepartService;
import com.zhonghe.active4j.system.service.SysMenuService;
import com.zhonghe.active4j.system.service.SysRoleService;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysCommonController.java
 * @description 
		  系统管理共用页面跳转
 * @time  2019年12月3日 上午10:11:51
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/common")
@Slf4j
public class SysCommonController extends BaseController {

	
	@Autowired
	private SysDepartService sysDepartService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 公共弹出页面,  部门的选择
	 * @return
	 */
	@RequestMapping("/selectdept")
	public String selectDept(Model model) {
		QueryWrapper<SysDepartEntity> queryWrapper = new QueryWrapper<SysDepartEntity>();
		queryWrapper.isNull("PARENT_ID");
		queryWrapper.orderByAsc("ORDER_NO");
		//顶级父级部门
		List<SysDepartEntity> lstParentDeparts = sysDepartService.list(queryWrapper);
		
		//拼接
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		departContact(lstParentDeparts, sb);
		sb = sb.append("]");
		
		//传值
		model.addAttribute("treeData", sb.toString());
		
		return "common/selectdept.html";
	}
	
	
	/**
	 * 采用递归的方式，拼接父子部门
	 * @param lstDeparts
	 * @param sb
	 */
	private void departContact(List<SysDepartEntity> lstDeparts, StringBuffer sb) {
		if(null != lstDeparts && lstDeparts.size() > 0) {
			for(int i = 0; i < lstDeparts.size(); i++) {
				SysDepartEntity depart = lstDeparts.get(i);
				//赋值树形结构
				sb = sb.append("{").append("text:").append("\"").append(depart.getShortName()).append("\",").append("id:").append("\"").append(depart.getId()).append("\"");
				//查询子部门
				QueryWrapper<SysDepartEntity> queryWrapper = new QueryWrapper<SysDepartEntity>();
				queryWrapper.eq("PARENT_ID", depart.getId());
				queryWrapper.orderByAsc("ORDER_NO");
				List<SysDepartEntity> lstChildren = sysDepartService.list(queryWrapper);
				
				if(null != lstChildren && lstChildren.size() > 0) {
					sb = sb.append(", nodes: [");
					//递归
					departContact(lstChildren, sb);
					sb.append("]");
				}
				
				if(i == lstDeparts.size() - 1) {
					sb = sb.append("}");
				}else {
					sb = sb.append("},");
				}
			}
		}
	}
	
	
	
	/**
	 * 公共弹出页面,  菜单的选择
	 * @return
	 */
	@RequestMapping("/selectmenu")
	public String selectMenu(Model model) {
		QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<SysMenuEntity>();
		queryWrapper.isNull("PARENT_ID");
		queryWrapper.orderByAsc("ORDER_NO");
		//顶级父级菜单
		List<SysMenuEntity> lstParentMenus = sysMenuService.list(queryWrapper);
		
		//拼接
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		menuContact(lstParentMenus, sb);
		sb = sb.append("]");
		
		//传值
		model.addAttribute("treeData", sb.toString());
		
		return "common/selectmenu.html";
	}
	
	/**
	 * 采用递归的方式，拼接父子菜单
	 * @param lstMenus
	 * @param sb
	 */
	private void menuContact(List<SysMenuEntity> lstMenus, StringBuffer sb) {
		if(null != lstMenus && lstMenus.size() > 0) {
			for(int i = 0; i < lstMenus.size(); i++) {
				SysMenuEntity menu = lstMenus.get(i);
				//赋值树形结构
				sb = sb.append("{").append("text:").append("\"").append(menu.getName()).append("\",").append("id:").append("\"").append(menu.getId()).append("\"");
				//查询子部门
				QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<SysMenuEntity>();
				queryWrapper.eq("PARENT_ID", menu.getId());
				queryWrapper.orderByAsc("ORDER_NO");
				List<SysMenuEntity> lstChildren = sysMenuService.list(queryWrapper);
				
				if(null != lstChildren && lstChildren.size() > 0) {
					sb = sb.append(", nodes: [");
					//递归
					menuContact(lstChildren, sb);
					sb.append("]");
				}
				
				if(i == lstMenus.size() - 1) {
					sb = sb.append("}");
				}else {
					sb = sb.append("},");
				}
			}
		}
	}
	
	/**
	 * 公共弹出页面,  角色的选择
	 * @return
	 */
	@RequestMapping("/selectrole")
	public String selectRole(Model model) {
		QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<SysRoleEntity>();
		queryWrapper.isNull("PARENT_ID");
		queryWrapper.orderByAsc("ORDER_NO");
		//顶级父级菜单
		List<SysRoleEntity> lstParentRoles = sysRoleService.list(queryWrapper);
		
		//拼接
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		roleContact(lstParentRoles, sb, null);
		sb = sb.append("]");
		
		//传值
		model.addAttribute("treeData", sb.toString());
		
		return "common/selectrole.html";
	}
	
	/**
	 * 公共弹出页面,  角色的选择
	 * @return
	 */
	@RequestMapping("/selectroles")
	public String selectRoles(Model model, String roleIds) {
		QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<SysRoleEntity>();
		queryWrapper.isNull("PARENT_ID");
		queryWrapper.orderByAsc("ORDER_NO");
		//顶级父级菜单
		List<SysRoleEntity> lstParentRoles = sysRoleService.list(queryWrapper);
		
		//拼接
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		roleContact(lstParentRoles, sb, roleIds);
		sb = sb.append("]");
		
		//传值
		model.addAttribute("treeData", sb.toString());
		
		return "common/selectroles.html";
	}
	
	/**
	 * 采用递归的方式，拼接父子菜单
	 * @param lstMenus
	 * @param sb
	 */
	private void roleContact(List<SysRoleEntity> lstParentRoles, StringBuffer sb, String roleIds) {
		if(null != lstParentRoles && lstParentRoles.size() > 0) {
			for(int i = 0; i < lstParentRoles.size(); i++) {
				SysRoleEntity role = lstParentRoles.get(i);
				if(StringUtils.isNotEmpty(roleIds) && StringUtils.contains(roleIds, role.getId())) {
					//赋值树形结构
					sb = sb.append("{").append("text:").append("\"").append(role.getName()).append("\",").append("state:{").append("checked:true").append("},").append("id:").append("\"").append(role.getId()).append("\"");
				}else {
					//赋值树形结构
					sb = sb.append("{").append("text:").append("\"").append(role.getName()).append("\",").append("id:").append("\"").append(role.getId()).append("\"");
				}
				
				//查询子部门
				QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<SysRoleEntity>();
				queryWrapper.eq("PARENT_ID", role.getId());
				queryWrapper.orderByAsc("ORDER_NO");
				List<SysRoleEntity> lstChildren = sysRoleService.list(queryWrapper);
				
				if(null != lstChildren && lstChildren.size() > 0) {
					sb = sb.append(", nodes: [");
					//递归
					roleContact(lstChildren, sb, roleIds);
					sb.append("]");
				}
				
				if(i == lstParentRoles.size() - 1) {
					sb = sb.append("}");
				}else {
					sb = sb.append("},");
				}
			}
		}
	}
	
	/**
	 * 根据角色的ID，获取权限配置
	 */
	@RequestMapping("/getmenutree")
	@ResponseBody
	public AjaxJson getMenuTree(@RequestParam String roleId, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			//查询之前的配置
			List<String> lstMenusIds = sysRoleService.getMenuByRole(roleId);
			
			QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<SysMenuEntity>();
			queryWrapper.isNull("PARENT_ID");
			queryWrapper.orderByAsc("ORDER_NO");
			//顶级父级菜单
			List<SysMenuEntity> lstParentMenus = sysMenuService.list(queryWrapper);
			//拼接
			StringBuffer sb = new StringBuffer();
			sb = sb.append("[");
			roleMenuContact(lstMenusIds, lstParentMenus, sb);
			sb = sb.append("]");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", sb.toString());
			j.setAttributes(map);
		}catch(Exception e) {
			log.error("配置权限取菜单出错，错误信息:" + e.getMessage());
			j.setSuccess(false);
		}
		
		return j;
	}
	
	/**
	 * 采用递归的方式，拼接父子菜单
	 * @param lstMenus
	 * @param sb
	 */
	private void roleMenuContact(List<String> lstMenusIds, List<SysMenuEntity> lstMenus, StringBuffer sb) {
		if(null != lstMenus && lstMenus.size() > 0) {
			for(int i = 0; i < lstMenus.size(); i++) {
				SysMenuEntity menu = lstMenus.get(i);
				if(lstMenusIds.size() > 0 && lstMenusIds.contains(menu.getId())) {
					//赋值树形结构
					sb = sb.append("{").append("text:").append("\"").append(menu.getName()).append("\",").append("state:{").append("checked:true").append("},").append("id:").append("\"").append(menu.getId()).append("\"");
				}else {
					//赋值树形结构
					sb = sb.append("{").append("text:").append("\"").append(menu.getName()).append("\",").append("id:").append("\"").append(menu.getId()).append("\"");
				}
				
				//查询子部门
				QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<SysMenuEntity>();
				queryWrapper.eq("PARENT_ID", menu.getId());
				queryWrapper.orderByAsc("ORDER_NO");
				List<SysMenuEntity> lstChildren = sysMenuService.list(queryWrapper);
				
				if(null != lstChildren && lstChildren.size() > 0) {
					sb = sb.append(", nodes: [");
					//递归
					roleMenuContact(lstMenusIds, lstChildren, sb);
					sb.append("]");
				}
				
				if(i == lstMenus.size() - 1) {
					sb = sb.append("}");
				}else {
					sb = sb.append("},");
				}
			}
		}
	}
	
}

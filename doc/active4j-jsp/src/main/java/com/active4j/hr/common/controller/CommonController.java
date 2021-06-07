package com.active4j.hr.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.system.entity.SysDeptEntity;
import com.active4j.hr.system.entity.SysFunctionEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysRoleFunctionEntity;
import com.active4j.hr.system.service.SysDeptService;
import com.active4j.hr.system.service.SysFunctionService;
import com.active4j.hr.system.service.SysRoleService;


/**
 * 通用业务处理
 * 
 * 
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysFunctionService sysFunctionService;

	/**
	 * icon弹出框
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/selectIcon")
	public ModelAndView selectIcon(HttpServletRequest req) {
		ModelAndView view = new ModelAndView("system/common/selectIcon");
		
		return view;
	}
	
	
	/**
	 * 部门选择弹出框
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/selectDepart")
	public ModelAndView selectDepart(HttpServletRequest req) {
		ModelAndView view = new ModelAndView("system/common/selectdepart");
		
		//查询部门
		//先查询顶级部门
		List<SysDeptEntity> lstDeparts = sysDeptService.getParentDepts();
		
		//拼接成bootstrap treeview树形结构
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		departContact(lstDeparts, sb);
		sb = sb.append("]");
		req.setAttribute("treeData", sb.toString());
		return view;
	}
	
	
	/**
	 * 递归的方式 循环显示各部门及其子部门
	 * @param lstDeparts
	 * @param sb
	 */
	private void departContact(List<SysDeptEntity> lstDeparts, StringBuffer sb) {
		if(null != lstDeparts && lstDeparts.size() > 0) {
			for(int i = 0; i < lstDeparts.size(); i++) {
				SysDeptEntity depart = lstDeparts.get(i);
				//查询所有组织架构
				sb = sb.append("{").append("text:").append("\"").append(depart.getName()).append("\",").append("id:").append("\"").append(depart.getId()).append("\"");
				
				List<SysDeptEntity> lstChild = sysDeptService.getChildDeptsByDeptId(depart.getId());
				
				if(null != lstChild && lstChild.size() > 0) {
					//根据type过滤数据
					List<SysDeptEntity> lstTmp = new ArrayList<SysDeptEntity>();
					for(SysDeptEntity d : lstChild) {
						lstTmp.add(d);
					}
					if(lstTmp.size() > 0) {
						sb = sb.append(", nodes: [");
						departContact(lstTmp, sb);
						sb.append("]");
					}
					
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
	 * 获取菜单数据，树形视图
	 * @param req
	 * @return
	 */
	@RequestMapping("/getMenusTreeDate")
	@ResponseBody
	public AjaxJson getMenusTreeDate(String roleId, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		
		//查询角色
		SysRoleEntity role = sysRoleService.getById(roleId);
		
		// 已有权限菜单
		List<String> loginActionlist = new ArrayList<String>();
		if (null != role) {
			List<SysRoleFunctionEntity> roleFunctionList = sysRoleService.getRoleFunctionList(role);
			//角色拥有的权限集合
			if(null != roleFunctionList && roleFunctionList.size() > 0) {
				for (SysRoleFunctionEntity roleFunction : roleFunctionList) {
					loginActionlist.add(roleFunction.getMenuId());
				}
			}
		}
		
		//先查询顶级菜单
		List<SysFunctionEntity> lstMenus = sysFunctionService.getParentFunctions();
		//拼接成bootstrap treeview树形结构
		StringBuffer sb = new StringBuffer();
		sb = sb.append("[");
		//采用递归方式拼接
		menuContact(lstMenus, sb, loginActionlist);
		sb = sb.append("]");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", sb.toString());
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 递归的方式 循环显示各菜单及其子菜单
	 * @param lstDeparts
	 * @param sb
	 */
	private void menuContact(List<SysFunctionEntity> lstMenus, StringBuffer sb, List<String> loginActionlist) {
		if(null != lstMenus && lstMenus.size() > 0) {
			for(int i = 0; i < lstMenus.size(); i++) {
				SysFunctionEntity function = lstMenus.get(i);
				if(loginActionlist.contains(function.getId())) {
					//已拥有的菜单权限
					sb = sb.append("{").append("text:").append("\"").append(function.getName()).append("\",").append("state:{").append("checked:true").append("},").append("id:").append("\"").append(function.getId()).append("\"");
				}else {
					//未拥有的菜单权限
					sb = sb.append("{").append("text:").append("\"").append(function.getName()).append("\",").append("id:").append("\"").append(function.getId()).append("\"");
				}
				
				List<SysFunctionEntity> lstChildren = sysFunctionService.getChildFunctionsByParentId(function.getId());
				if(null != lstChildren && lstChildren.size() > 0) {
					sb = sb.append(", nodes: [");
					menuContact(lstChildren, sb, loginActionlist);
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

package com.zhonghe.active4j.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.MyBeanUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.service.SysRoleService;
import com.zhonghe.active4j.system.wrapper.RoleWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysRoleController.java
 * @description 
		  角色管理
 * @time  2019年12月3日 上午10:14:15
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/role")
@Slf4j
public class SysRoleController extends BaseController {
	
	@Autowired
	private SysRoleService sysRoleService;

	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "sys/role/role_list.html";
	}
	
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysDepartEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysRoleEntity sysRoleEntity, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysRoleEntity> queryWrapper = QueryUtils.installQueryWrapper(sysRoleEntity, request.getParameterMap());
		
		//执行查询
		List<SysRoleEntity> lstResult = sysRoleService.list(queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new RoleWrapper(lstResult).wrap());
	}
	
	
	/**
	 * 跳转到页面
	 * @param sysRoleEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(SysRoleEntity sysRoleEntity, Model model) {
		
		if(StringUtils.isNotEmpty(sysRoleEntity.getId())) {
			SysRoleEntity role = sysRoleService.getById(sysRoleEntity.getId());
			model.addAttribute("role", role);
			
			if(StringUtils.isNotEmpty(role.getParentId())) {
				SysRoleEntity parent = sysRoleService.getById(role.getParentId());
				model.addAttribute("parentName", parent.getName());
			}
		}
		
		return "sys/role/role.html";
	}
	
	/**
	 * 保存方法
	 * @param sysRoleEntity
	 * @param parentId         上级
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存角色信息", memo = "新增或编辑保存了角色信息")
	public AjaxJson save(@Validated SysRoleEntity sysRoleEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//设为空
			if(StringUtils.isEmpty(sysRoleEntity.getParentId())) {
				sysRoleEntity.setParentId(null);
			}
			
			if(StringUtils.isEmpty(sysRoleEntity.getId())) {
				//新增保存
				sysRoleService.save(sysRoleEntity);
			}else {
				//编辑保存
				SysRoleEntity tmp = sysRoleService.getById(sysRoleEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysRoleEntity, tmp);
				
				sysRoleService.saveOrUpdate(tmp);
				
			}
		}catch(Exception e) {
			j.setSuccess(false);
			log.error("保存角色信息报错，错误信息:" + e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 *  删除操作
	 * @param sysRoleEntity
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除角色信息", memo = "删除了角色信息")
	public AjaxJson delete(SysRoleEntity sysRoleEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isNotEmpty(sysRoleEntity.getId())) {
				//删除前校验该角色下有没有相关用户
				List<SysUserEntity> lstUsers = sysRoleService.getUsersByRole(sysRoleEntity);
				if(null != lstUsers && lstUsers.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该角色下存在用户配置，不能直接删除");
					return j;
				}
				
				//删除前校验该角色下是否存在下属角色
				List<SysRoleEntity> lstRoles = sysRoleService.getChildRolesByRole(sysRoleEntity);
				if(null != lstRoles && lstRoles.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该角色下存在下属角色，不能直接删除");
					return j;
				}
				
				sysRoleService.deleteRole(sysRoleEntity);
			}
			
		}catch(Exception e) {
			log.error("删除角色信息出错，错误信息:" + e.getMessage() );
			j.setSuccess(false);
			j.setMsg("删除角色错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 保存权限
	 * @param roleId
	 * @param roleMenuIds
	 * @return
	 */
	@RequestMapping("/saverolemenu")
	@RequiresPermissions("sys:role:saverolemenu")
	@ResponseBody
	@Log(type = LogType.update, name = "更新权限配置", memo = "保存更新了权限配置")
	public AjaxJson saveRoleMenu(String roleId, String roleMenuIds)  {
		AjaxJson j = new AjaxJson();
		try {
			
			//保存权限配置
			sysRoleService.saveRoleMenu(roleId, roleMenuIds);
			
			j.setMsg("权限配置成功");
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("权限配置遇到异常");
			log.error("权限配置遇到错误，错误信息:" + e.getMessage());
		}
		
		return j;
	}
}

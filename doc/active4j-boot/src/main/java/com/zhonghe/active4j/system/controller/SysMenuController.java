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
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.service.SysMenuService;
import com.zhonghe.active4j.system.wrapper.MenuWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysMenuController.java
 * @description 
		  菜单管理
 * @time  2019年12月3日 上午10:13:57
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/menu")
@Slf4j
public class SysMenuController extends BaseController {

	
	@Autowired
	private SysMenuService sysMenuService;
	
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "sys/menu/menu_list.html";
	}
	
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysDepartEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysMenuEntity sysMenuEntity, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysMenuEntity> queryWrapper = QueryUtils.installQueryWrapper(sysMenuEntity, request.getParameterMap());
		
		//执行查询
		List<SysMenuEntity> lstResult = sysMenuService.list(queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new MenuWrapper(lstResult).wrap());
	}
	
	
	/**
	 * 跳转到页面
	 * @param sysMenuEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(SysMenuEntity sysMenuEntity, Model model) {
		
		if(StringUtils.isNotEmpty(sysMenuEntity.getId())) {
			SysMenuEntity menu = sysMenuService.getById(sysMenuEntity.getId());
			model.addAttribute("menu", menu);
			
			if(StringUtils.isNotEmpty(menu.getParentId())) {
				SysMenuEntity parent = sysMenuService.getById(menu.getParentId());
				model.addAttribute("parentName", parent.getName());
			}
		}
		
		return "sys/menu/menu.html";
	}
	
	
	/**
	 * 保存方法
	 * @param sysMenuEntity
	 * @param parentId         上级菜单
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存菜单信息", memo = "新增或编辑保存了菜单信息")
	public AjaxJson save(@Validated SysMenuEntity sysMenuEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			
			//设为空
			if(StringUtils.isEmpty(sysMenuEntity.getParentId())) {
				sysMenuEntity.setParentId(null);
			}
			
			if(StringUtils.isEmpty(sysMenuEntity.getId())) {
				//新增保存
				sysMenuService.save(sysMenuEntity);
			}else {
				//编辑保存
				SysMenuEntity tmp = sysMenuService.getById(sysMenuEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysMenuEntity, tmp);
				
				sysMenuService.saveOrUpdate(tmp);
				
			}
		}catch(Exception e) {
			log.error("保存菜单信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		
		return j;
	}
	
	/**
	 *  删除操作
	 * @param sysMenuEntity
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除菜单信息", memo = "删除了菜单信息")
	public AjaxJson delete(SysMenuEntity sysMenuEntity) {
		AjaxJson j = new AjaxJson();
		try {
			
			if(StringUtils.isNotEmpty(sysMenuEntity.getId())) {
				//删除之前校验是否存在子菜单
				List<SysMenuEntity> lstMenus = sysMenuService.getChildMenusByMenu(sysMenuEntity);
				if(null != lstMenus && lstMenus.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该菜单下存在子级菜单，不能直接删除");
					return j;
				}
				
				sysMenuService.deleteMenu(sysMenuEntity);
			}
			
		}catch(Exception e) {
			log.error("删除菜单信息出错，错误信息:" + e.getMessage() );
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		
		return j;
	}
	
}

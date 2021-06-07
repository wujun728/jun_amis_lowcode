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
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.service.SysDepartService;
import com.zhonghe.active4j.system.service.SystemService;
import com.zhonghe.active4j.system.wrapper.DeptWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysDeptController.java
 * @description 
		  部门管理
 * @time  2019年12月3日 上午10:12:17
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController extends BaseController {
	
	@Autowired
	private SysDepartService sysDepartService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "sys/dept/dept_list.html";
	}
	
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysDepartEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysDepartEntity sysDepartEntity, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysDepartEntity> queryWrapper = QueryUtils.installQueryWrapper(sysDepartEntity, request.getParameterMap());
		
		//执行查询
		List<SysDepartEntity> lstResult = sysDepartService.list(queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new DeptWrapper(lstResult).wrap());
	}
	
	
	/**
	 * 跳转到页面
	 * @param sysDepartEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(SysDepartEntity sysDepartEntity, Model model) {
		
		if(StringUtils.isNotEmpty(sysDepartEntity.getId())) {
			SysDepartEntity depart = sysDepartService.getById(sysDepartEntity.getId());
			model.addAttribute("depart", depart);
			
			if(StringUtils.isNotEmpty(depart.getParentId())) {
				SysDepartEntity parent = sysDepartService.getById(depart.getParentId());
				model.addAttribute("parentName", parent.getShortName());
			}
		}
		
		return "sys/dept/dept.html";
	}
	
	/**
	 * 保存方法
	 * @param sysDepartEntity
	 * @param parentId         上级部门
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存部门信息", memo = "新增或编辑保存了部门信息")
	public AjaxJson save(@Validated SysDepartEntity sysDepartEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//设为空
			if(StringUtils.isEmpty(sysDepartEntity.getParentId())) {
				sysDepartEntity.setParentId(null);
			}
			
			if(StringUtils.isEmpty(sysDepartEntity.getId())) {
				//新增保存
				sysDepartService.save(sysDepartEntity);
			}else {
				//编辑保存
				SysDepartEntity tmp = sysDepartService.getById(sysDepartEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysDepartEntity, tmp);
				
				sysDepartService.saveOrUpdate(tmp);
				
			}
			//刷新部门数据
			systemService.initDeparts();
		}catch(Exception e) {
			log.error("保存部门信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败");
			e.printStackTrace();
		}
		
		
		return j;
	}
	
	
	/**
	 *  删除操作
	 * @param sysDepartEntity
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除部门信息", memo = "删除了部门信息")
	public AjaxJson delete(SysDepartEntity sysDepartEntity) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isNotEmpty(sysDepartEntity.getId())) {
				
				//查询部门下面的用户，存在用户则不能删除该部门
				List<SysUserEntity> lstUsers = sysDepartService.getUsersByDept(sysDepartEntity);
				if(null != lstUsers && lstUsers.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该部门下存在用户，不能直接删除");
					return j;
				}
				
				List<SysDepartEntity> lstDepts = sysDepartService.getChildDepartsByDept(sysDepartEntity);
				if(null != lstDepts && lstDepts.size() > 0) {
					j.setSuccess(false);
					j.setMsg("该部门下存在子部门，不能直接删除");
					return j;
				}
				
				sysDepartService.removeById(sysDepartEntity.getId());
				//刷新部门数据
				systemService.initDeparts();
			}
			
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("部门删除错误!");
			log.error("删除部门信息出错，错误信息:" + e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
	
}

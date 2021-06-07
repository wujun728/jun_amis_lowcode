package com.zhonghe.active4j.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.MyBeanUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.service.SysDepartService;
import com.zhonghe.active4j.system.service.SysUserService;
import com.zhonghe.active4j.system.wrapper.UserWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysUserController.java
 * @description 
		  用户管理
 * @time  2019年12月3日 上午10:14:28
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysDepartService sysDepartService;
	
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "sys/user/user_list.html";
	}
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysUserEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysUserEntity sysUserEntity, PageInfo<SysUserEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysUserEntity> queryWrapper = QueryUtils.installQueryWrapper(sysUserEntity, request.getParameterMap());
		
		//执行查询
		IPage<SysUserEntity> lstResult = sysUserService.page(page.getPageEntity(), queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new UserWrapper(lstResult).wrap());
	}
	
	/**
	 * 跳转到页面
	 * @param sysDepartEntity
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(SysUserEntity sysUserEntity, Model model) {
		
		if(StringUtils.isNotEmpty(sysUserEntity.getId())) {
			SysUserEntity user = sysUserService.getById(sysUserEntity.getId());
			model.addAttribute("user", user);
			
			//所属部门
			SysDepartEntity dept = sysDepartService.getById(user.getDeptId());
			model.addAttribute("deptName", dept.getName());
			
			//所属角色
			List<SysRoleEntity> lstRoles = sysUserService.getUserRoles(user);
			StringBuffer sbRoleIds = new StringBuffer();
			StringBuffer sbRoleNames = new StringBuffer();
			for(SysRoleEntity role : lstRoles) {
				sbRoleIds = sbRoleIds.append(role.getId()).append(",");
				sbRoleNames = sbRoleNames.append(role.getName()).append(",");
			}
			if(sbRoleIds.length() > 0) {
				model.addAttribute("roleNames", sbRoleNames.substring(0, sbRoleNames.length() - 1));
				model.addAttribute("roleIds", sbRoleIds.substring(0, sbRoleIds.length() - 1));
			}
		}
		
		return "sys/user/user.html";
	}
	
	
	/**
	 * 保存方法
	 * @param sysUserEntity
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存用户信息", memo = "新增或编辑保存了用户信息")
	public AjaxJson save(SysUserEntity sysUserEntity, String roleIds)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//后端校验
			if(StringUtils.isEmpty(sysUserEntity.getDeptId())) {
				j.setSuccess(false);
				j.setMsg("所属部门不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getRealName())) {
				j.setSuccess(false);
				j.setMsg("用户姓名不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getSex())) {
				j.setSuccess(false);
				j.setMsg("用户性别不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getStatus())) {
				j.setSuccess(false);
				j.setMsg("用户状态不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getTelNo())) {
				j.setSuccess(false);
				j.setMsg("用户手机号不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getUserName())) {
				j.setSuccess(false);
				j.setMsg("用户名不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getUserNo())) {
				j.setSuccess(false);
				j.setMsg("用户编号不能为空");
				return j;
			}
			
			//新增保存
			if(StringUtils.isEmpty(sysUserEntity.getId())) {
				//初始化密码
				sysUserEntity.setSalt(ShiroUtils.getRandomSalt());
				sysUserEntity.setPassword(ShiroUtils.md5("123456", sysUserEntity.getSalt()));
				
				sysUserService.saveUser(sysUserEntity, roleIds);
			}
			//编辑保存
			else {
				SysUserEntity tmp = sysUserService.getById(sysUserEntity.getId());
				MyBeanUtils.copyBeanNotNull2Bean(sysUserEntity, tmp);
				tmp.setSalt(ShiroUtils.getRandomSalt());
				tmp.setPassword(ShiroUtils.md5("123456", tmp.getSalt()));
				sysUserService.saveOrUpdateUser(tmp, roleIds);
			}
			
		}catch(Exception e) {
			log.error("保存用户信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	
	/**
	 *  删除操作
	 * @param sysUserEntity
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除用户信息", memo = "删除了用户信息")
	public AjaxJson delete(SysUserEntity sysUserEntity) {
		AjaxJson j = new AjaxJson();
		try {
			
			if(StringUtils.isNotEmpty(sysUserEntity.getId())) {
				String userId = ShiroUtils.getSessionUserId();
				if(StringUtils.equals(userId, sysUserEntity.getId())) {
					j.setSuccess(false);
					j.setMsg("不允许删除自身账户");
					return j;
				}
				
				sysUserService.delete(sysUserEntity);
			}
			
		}catch(Exception e) {
			log.error("删除用户信息出错，错误信息:" + e.getMessage() );
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 跳转到基本信息页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/basic")
	public String basic(Model model) {
		//获取登录用户id
		String userId = ShiroUtils.getSessionUser().getId();
		
		SysUserEntity user = sysUserService.getById(userId);
		model.addAttribute("user", user);
		SysDepartEntity depart = sysDepartService.getById(user.getDeptId());
		if(null != depart) {
			model.addAttribute("depart", depart.getName());
		}
		
		return "sys/user/basic/basic.html";
	}
	
	/**
	 * 
	 * @description
	 *  	保存用户基本信息
	 * @params
	 *      sysUserEntity
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月19日 下午4:30:39
	 */
	@RequestMapping("/savebasic")
	@ResponseBody
	@Log(type = LogType.save, name = "保存用户基本信息", memo = "修改保存了用户管理的基本信息")
	public AjaxJson saveBasic(SysUserEntity sysUserEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//后端校验
			if(StringUtils.isEmpty(sysUserEntity.getRealName())) {
				j.setSuccess(false);
				j.setMsg("姓名不能为空");
				return j;
			}
			
			if(sysUserEntity.getRealName().length() > 45) {
				j.setSuccess(false);
				j.setMsg("姓名输入长度超过45个字");
				return j;
			}
			
			if(StringUtils.isEmpty(sysUserEntity.getSex())) {
				j.setSuccess(false);
				j.setMsg("请选择性别");
				return j;
			}
			
			if(sysUserEntity.getTelNo().length() > 20) {
				j.setSuccess(false);
				j.setMsg("请输入正确的手机号");
				return j;
			}
			
			if(sysUserEntity.getEmail().length() > 250) {
				j.setSuccess(false);
				j.setMsg("请输入正确的邮箱");
				return j;
			}
			
			//编辑保存
			SysUserEntity tmp = sysUserService.getById(sysUserEntity.getId());
			MyBeanUtils.copyBeanNotNull2Bean(sysUserEntity, tmp);
			
			sysUserService.saveOrUpdate(tmp);
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("sorry,系统一定哪个地方出错了");
			log.error("保存用户基本信息报错，错误信息:{}", e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到修改密码页面
	 * @params
	 *      model
	 * @return String
	 * @author mhm
	 * @time 2019年12月19日 下午1:45:40
	 */
	@RequestMapping("/password")
	public String password(Model model) {
		return "sys/user/basic/password.html";
	}
	
	/**
	 * 
	 * @description
	 *  	修改密码
	 * @params
	 *      oldPassword
	 *      password
	 *      repassword
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月19日 下午1:46:54
	 */
	@RequestMapping("/savepwd")
	@ResponseBody
	@Log(type = LogType.save, name = "保存密码", memo = "用户修改保存了密码")
	public AjaxJson savePwd(String oldPassword, String password, String repassword)  {
		AjaxJson j = new AjaxJson();
		
		try{
			if(StringUtils.isEmpty(oldPassword)) {
				j.setSuccess(false);
				j.setMsg("请输入当前密码");
				return j;
			}
			
			if(StringUtils.isEmpty(password)) {
				j.setSuccess(false);
				j.setMsg("请输入新密码");
				return j;
			}
			
			if(StringUtils.isEmpty(repassword)) {
				j.setSuccess(false);
				j.setMsg("请确认新密码");
				return j;
			}
			//获取登录用户id
			String userId = ShiroUtils.getSessionUser().getId();
			//根据id获取用户
			SysUserEntity user = sysUserService.getById(userId);
			//校验当前密码
			if(!StringUtils.equals(ShiroUtils.md5(oldPassword, user.getSalt()), user.getPassword())) {
				j.setSuccess(false);
				j.setMsg("当前密码不正确");
				return j;
			}
			
			String pwd = StringUtils.trim(password);
			if(pwd.length() < 6 || pwd.length() > 12) {
				j.setSuccess(false);
				j.setMsg("密码必须6到12位，且不能出现空格");
				return j;
			}
			
			if(!StringUtils.equals(pwd, repassword)) {
				j.setSuccess(false);
				j.setMsg("两次密码输入不一致");
				return j;
			}
			
			user.setSalt(ShiroUtils.getRandomSalt());
			user.setPassword(ShiroUtils.md5(pwd, user.getSalt()));
			//更新密码
			sysUserService.saveOrUpdate(user);
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("sorry,系统一定哪个地方出错了");
			log.error("系统用户修改密码报错，错误信息{}", e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
}

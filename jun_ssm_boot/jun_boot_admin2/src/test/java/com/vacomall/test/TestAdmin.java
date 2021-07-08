package com.jun.plugin.api.test;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jun.plugin.api.entity.SysMenu;
import com.jun.plugin.api.entity.SysRole;
import com.jun.plugin.api.entity.SysUser;
import com.jun.plugin.api.service.ISysMenuService;
import com.jun.plugin.api.service.ISysRoleMenuService;
import com.jun.plugin.api.service.ISysRoleService;
import com.jun.plugin.api.service.ISysUserService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {
		"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-service.xml"
})
public class TestAdmin {
	
	@Autowired private ISysRoleService sysRoleService;
	@Autowired private ISysRoleMenuService sysRoleMenuService;
	@Autowired private ISysMenuService sysMenuService;
	@Autowired private ISysUserService sysUserService;
	
	/**
	 * 创建一个Admin用户
	 */
	@Test
	public void addAdmin() {
		
		//创建角色
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("超级管理员");
		sysRole.setRoleState(1);
		sysRole.setCreateTime(new Date());
		sysRoleService.insert(sysRole);
		//查询权限
		List<Object> list = sysMenuService.selectObjs(new EntityWrapper<SysMenu>().setSqlSelect("id"));
		//分配权限
		sysRoleMenuService.addAuth(sysRole.getId(),list.toArray(new String[list.size()]));
		//添加用户
		SysUser user = new SysUser();
		user.setUserState(1);
		user.setUserName("admin");
		user.setPassword("123456");
		user.setCreateTime(new Date());
		sysUserService.insertUser(user, new String[]{sysRole.getId()});
		
	}
	
	/**
	 * 修改Admin的密码位123456
	 */
	@Test
	public void changeAdminPwd() {
		// MD5,"密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "123456", "admin", 1024).toString();
		System.out.println(pwd);
	}

}

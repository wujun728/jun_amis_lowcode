package com.zhonghe.active4j.core.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.zhonghe.active4j.core.session.GlobalSessionConstant;
import com.zhonghe.active4j.core.util.ApplicationContextUtil;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.monitor.service.OnlineSessionService;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.model.ActiveUser;
import com.zhonghe.active4j.system.service.SysUserService;

public class MyShiroRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		ActiveUser user = (ActiveUser) principals.getPrimaryPrincipal();

		// 基于角色的权限
		List<SysRoleEntity> lstRoles = user.getRoles();
		Set<String> lstStrRoles = new HashSet<String>();
		if (null != lstRoles && lstRoles.size() > 0) {
			for (SysRoleEntity role : lstRoles) {
				lstStrRoles.add(role.getRoleNo());
			}
		}
		// 基于资源的权限
		List<SysMenuEntity> lstMenus = user.getPermissions();
		Set<String> lstStrMenus = new HashSet<String>();
		if (null != lstMenus && lstMenus.size() > 0) {
			for (SysMenuEntity menu : lstMenus) {
				if (StringUtils.isNotEmpty(menu.getUrl())) {
					lstStrMenus.add(menu.getUrl());
				}
			}
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(lstStrMenus);
		info.addRoles(lstStrRoles);
		return info;
	}

	/**
	 * 认证信息 登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// 根据用户名查询用户
		SysUserService userService = ApplicationContextUtil.getContext().getBean(SysUserService.class);

		// 用户名
		String userName = (String) authcToken.getPrincipal();

		// 查询用户
		SysUserEntity sysUser = userService.getUserByUseName(userName);
		if (null == sysUser) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		if (StringUtils.equals(sysUser.getStatus(), "0")) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		ActiveUser user = userService.getActiveUserByUser(sysUser);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());

		ShiroUtils.getSession().setAttribute(GlobalSessionConstant.SESSION_USER, user);
		
		/**
		 * 保存session进入DB，为了统计在线用户使用，不需要可以去除
		 */
		OnlineSessionService onlineSessionService = ApplicationContextUtil.getContext().getBean(OnlineSessionService.class);
		onlineSessionService.saveOnlineSession(ShiroUtils.getSession());
		
		return info;

	}

}

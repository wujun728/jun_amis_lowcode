package com.qingshop.mall.framework.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.modules.system.entity.SysMenu;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.service.ISysMenuService;
import com.qingshop.mall.modules.system.service.ISysUserService;

/**
 * shiro Realm
 *
 */
public class MallRealm extends AuthorizingRealm {

	/**
	 * 用户服务
	 */
	@Autowired
	private ISysUserService sysUserService;

	/**
	 * 角色菜单服务
	 */
	@Autowired
	private ISysMenuService sysMenuService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken user = (UsernamePasswordToken) token;
		SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("user_name", user.getUsername()));

		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		if (sysUser.getUserState() == 0) {
			throw new LockedAccountException();
		}
		// 盐值加密
		ByteSource byteSource = ByteSource.Util.bytes(user.getUsername());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), byteSource, getName());
		return info;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		Long userId = sysUser.getUserId();
		List<String> permsList;
		// 系统管理员，拥有最高权限
		if (sysUser.isAdmin()) {
			List<SysMenu> menuList = sysMenuService.list();
			permsList = new ArrayList<>(menuList.size());
			for (SysMenu menu : menuList) {
				permsList.add(menu.getResource());
			}
		} else {
			permsList = sysUserService.queryAllPermsByUserId(userId);
		}

		// 用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.add(perms.trim());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

}

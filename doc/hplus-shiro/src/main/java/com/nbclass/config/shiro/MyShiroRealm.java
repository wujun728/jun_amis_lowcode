package com.nbclass.config.shiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nbclass.common.constant.CoreConst;
import com.nbclass.common.util.IpUtil;
import com.nbclass.modules.sys.model.SysUser;
import com.nbclass.modules.sys.service.MenuService;
import com.nbclass.modules.sys.service.RoleService;
import com.nbclass.modules.sys.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("principals should not be null");
		}
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 超级管理员
		if (user.isAdmin()) {
			info.addRole("admin");
			info.addStringPermission("*");
		} else {
			info.setRoles(roleService.listRoleNameByUserId(user.getUserId()));
			info.setStringPermissions(menuService.listPermsByUserId(user.getUserId()));
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		SysUser user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		if (CoreConst.STATUS_INVALID.equals(user.getStatus())) {
			// 帐号锁定
			throw new LockedAccountException();
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 把ip放入user
		user.setLoginIpAddress(IpUtil.getIpAddr(request));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
		return authenticationInfo;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}

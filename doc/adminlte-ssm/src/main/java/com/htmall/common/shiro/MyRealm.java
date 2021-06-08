package com.htmall.common.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.htmall.entity.SysUser;
import com.htmall.service.ISysRoleMenuService;
import com.htmall.service.ISysUserRoleService;
import com.htmall.service.ISysUserService;

/**
 * shiro Realm
 */
public class MyRealm extends AuthorizingRealm {

	/**
	 * 用户服务
	 */
	@Autowired
	private ISysUserService userService;
	/**
	 * 用户角色服务
	 */
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	/**
	 * 角色菜单服务
	 */
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken user = (UsernamePasswordToken) token;
		SysUser sysUser = userService.getOne(new QueryWrapper<SysUser>().eq("user_name", user.getUsername()));

		if (sysUser == null) {
			throw new UnknownAccountException();
		}
		if (sysUser.getUserState() == SysUser._0) {
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
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = sysUserRoleService.findRolesByUid(sysUser.getId());
		Set<String> permissions = sysRoleMenuService.findMenusByUid(sysUser.getId());
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * 密码加密 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// MD5,"原密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "admin123", "admin", 1024).toString();
		System.out.println(pwd);
	}
}

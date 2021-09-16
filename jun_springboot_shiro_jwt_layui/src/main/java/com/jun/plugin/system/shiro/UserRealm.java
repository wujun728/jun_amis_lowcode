package com.jun.plugin.system.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.WebUtils;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.service.MenuService;
import com.jun.plugin.system.service.RoleService;
import com.jun.plugin.system.service.UserService;

import java.util.List;

/**
 * ClassName: UserRealm Description: layui date: 2020/4/14 20:30
 *
 * 
 * 
 * @since JDK 1.8
 */
public class UserRealm extends AuthorizingRealm {
	/**
	 * @Lazy 让代理先走 要不然 切面失效 缓存
	 */

	@Autowired
	@Lazy
	private UserService userService;

	@Autowired
	@Lazy
	private RoleService roleService;

	@Autowired
	@Lazy
	private MenuService menuService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 认证
	 *
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 获取认证信息
		String userName = authenticationToken.getPrincipal().toString();
		// 判断该信息是否存在
		User user = userService.queryUserByLoginName(userName);
		if (null != user) {
			// 储存起来
			ActiveUser activeUser = new ActiveUser();
			activeUser.setUser(user);
			// 根据用户ID 查询角色名称的集合
			List<String> roles = this.roleService.queryRoleNamesByUid(user.getId());
			// 根据用户ID 查询权限编码的集合
			List<String> permissions = this.menuService.queryPermissionCodesByUserId(user.getId());
			activeUser.setRoles(roles);
			activeUser.setPermissions(permissions);
			/* 设置当前用户 给到共享线程 */
			WebUtils.setThisName(user);
			// 进行认证
			return new SimpleAuthenticationInfo(activeUser, user.getPwd(), ByteSource.Util.bytes(user.getSalt()),
					getName());
		}
		return null;
	}

	/**
	 * 授权
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取认证传递过来的对象集
		ActiveUser active = (ActiveUser) principalCollection.getPrimaryPrincipal();
		// 获取权限
		List<String> permissions = active.getPermissions();
		System.out.println("权限:" + permissions);
		// 获取角色
		List<String> roles = active.getRoles();
		// 获取用户
		User user = active.getUser();
		// 判断是否为超级管理员
		if (user.getType().equals(Constant.USER_TYPE_SUPER)) {
			info.addStringPermission("*:*");
		} else {
			// 不是超级管理员
			if (null != roles && roles.size() > 0) {
				info.addRoles(roles);
			}
			if (null != permissions && permissions.size() > 0) {
				info.addStringPermissions(permissions);
			}
		}
		return info;
	}

}
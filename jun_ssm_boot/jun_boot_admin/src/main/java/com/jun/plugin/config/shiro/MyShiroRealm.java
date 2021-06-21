package com.jun.plugin.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.IpUtil;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.service.MenuService;
import com.jun.plugin.modules.sys.service.RoleService;
import com.jun.plugin.modules.sys.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;
	
    @Autowired
    private RedisSessionDAO redisSessionDAO;

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
	

    /**清除认证信息*/
    public void removeCachedAuthenticationInfo(List<String> userIds) {
        if(null == userIds || userIds.size() == 0)	{
            return ;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthenticationInfo(simplePrincipalCollection);
        }
    }
    
    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     * @param userIds 已经修改了权限的userId
     */
    public void clearAuthorizationByUserId(List<String> userIds){
        if(null == userIds || userIds.size() == 0)	{
            return ;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

    /**
     * 根据用户id获取所有spc
     * @param userIds 已经修改了权限的userId
     */
    private  List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds){
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof SysUser){
                	SysUser user = (SysUser) obj;
                    System.out.println("user:"+user);
                    //比较用户ID，符合即加入集合
                    if(null != user && userIds.contains(user.getUserId())){
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }

}

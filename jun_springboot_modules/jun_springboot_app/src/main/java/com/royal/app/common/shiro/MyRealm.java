package com.royal.app.common.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.royal.app.common.util.JwtUtil;
import com.royal.app.controller.sys.entity.AppUser;
import com.royal.app.controller.sys.service.impl.UserService;

@Service
public class MyRealm extends AuthorizingRealm
{
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService         userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = JwtUtil.getLoginname(principals.toString());
        AppUser appUser = userService.findByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 这里角色和权限都是演示所用，正式使用请根据自己业务修改
        simpleAuthorizationInfo.addRole(appUser.getRole());
        Set<String> permission = new HashSet<>(Arrays.asList(appUser.getPermission().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        logger.debug(username+" role:"+appUser.getRole()+" permission:"+permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException
    {
        logger.debug("————身份认证方法————");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getLoginname(token);
        if (username == null)
        {
            throw new AuthenticationException("token invalid");
        }
        AppUser userBean = userService.findByUsername(username);
        if (userBean == null)
        {
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JwtUtil.verify(token, username, userBean.getPassword()))
        {
            throw new AuthenticationException("token verify error");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
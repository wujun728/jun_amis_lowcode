package com.lanyu.common.filter;

import com.lanyu.admin.service.UserService;
import com.lanyu.common.model.Role;
import com.lanyu.common.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.selectByPhone(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<String>();
        Set<Role> roles = user.getRoleList();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        List<String> listrole = new ArrayList<String>();
        if(roles.size()>0) {
            for(Role role : roles) {
                if(!listrole.contains(role.getRcode())){
                    listrole.add(role.getRcode());
                }
                Set<String> modules = role.getModules();
                if(modules.size()>0) {
                    for(String module : modules) {
                        permissions.add(module);
                    }
                }
            }
        }
        info.addRoles(listrole);                       //将角色放入shiro中.
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}
package com.lanyu.common.Controller;

import com.lanyu.common.model.Role;
import com.lanyu.common.model.User;
import com.lanyu.common.model.vo.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 登录注册相关操作
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/loginpage")
    public String addUser(){
        return "login";
    }

    @RequestMapping("/loginUser")
    @ResponseBody
    public ResponseData loginUser(String username, String password, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        ResponseData res = new ResponseData();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            res.setSuccess(true);
        } catch (IncorrectCredentialsException e) {
            res.setSuccess(false);
            res.setMsg( "密码错误");
        } catch (LockedAccountException e) {
            res.setSuccess(false);
            res.setMsg( "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            res.setSuccess(false);
            res.setMsg(  "该用户不存在");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg(  "系统异常");
        }
        return res;
    }

    @RequestMapping(value = "/indexpage")
    public String logindo(HttpServletRequest request,HttpSession session){
        try {
            User user = (User) session.getAttribute("user");
            Role role = (Role) user.getRoles().toArray()[0];
            String rolename = role.getRname();
            request.setAttribute("rolename",rolename);
        } catch (Exception e) {
            return "login";
        }
        return "index";
    }
}

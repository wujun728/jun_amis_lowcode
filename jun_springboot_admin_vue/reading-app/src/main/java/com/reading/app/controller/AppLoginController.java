package com.reading.app.controller;


import com.reading.app.domain.User;
import com.reading.app.service.IUserService;
import com.reading.common.core.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.reading.app.domain.Login;
import com.reading.app.service.ILoginService;

@Controller
@RequestMapping("/app")
public class AppLoginController {

    @Autowired
    ILoginService loginService;

    @Autowired
    IUserService userService;

    @ApiOperation(value = "登录接口：账号，密码正确即可成功")
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody Login login) {
        if (login != null) {
            int ret = loginService.login(login);
            if (ret == 0) {
                return AjaxResult.error("用户名或密码错误");
            }else {
               User user =  userService.selectUserByAid(ret);
               return AjaxResult.success("登录成功",user);
            }
        }
        return AjaxResult.success("登录成功");
    }

    @ResponseBody
    @RequestMapping(value = "/changePwd",method = RequestMethod.PUT)
    public AjaxResult changePwd(@RequestBody Login login) {
        if (login != null) {
            int ret = loginService.changePwd(login);
            if (ret == 0) {
                return AjaxResult.error("用户不存在");
            }
        }
        return AjaxResult.success("密码修改成功");
    }


    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public AjaxResult register(@RequestBody Login login) {
        if (login != null) {
            int ret = loginService.register(login);
            if (ret == 0) {
                return AjaxResult.error("用户已存在");
            }
        }
        return AjaxResult.success("注册成功");
    }


    @ResponseBody
    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public String verifyCode(@RequestBody Login login) {
        return loginService.verifyCode(login);
    }
}
package com.ruoyi.framework.shiro.service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册校验方法
 * @author ruoyi
 */
@Component
public class RegisterService {
    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    public String register(HttpServletRequest request) {
        String login_name = RequestUtil.getValue(request, "login_name");
        String password = RequestUtil.getValue(request, "password");

        String msg = "";
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            msg = "验证码错误";
        } else if (StringUtils.isEmpty(login_name)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (login_name.length() < UserConstants.USERNAME_MIN_LENGTH
                || login_name.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(request))) {
            msg = "保存用户'" + login_name + "'失败，注册账号已存在";
        } else {
            boolean regFlag = userService.registerUser(request);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(login_name, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}

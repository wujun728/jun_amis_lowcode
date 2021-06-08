package com.hope.controller.base;

import com.hope.service.ValidateCodeService;
import com.hope.utils.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 图片验证码控制器
 *
 * @author aodeng
 */
@Controller
@Slf4j
public class KaptchaController {

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 获取验证码图片
     * Gets captcha code.
     *
     * @return the captcha code
     * @throws IOException the io exception
     */
    @RequestMapping("/verificationCode")
    @ResponseBody
    public AjaxResult getCaptchaCode() throws IOException {
        return validateCodeService.createCapcha();
    }
}

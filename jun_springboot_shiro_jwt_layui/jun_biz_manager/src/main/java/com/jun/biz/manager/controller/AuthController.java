package com.jun.biz.manager.controller;

import com.jun.biz.common.base.enums.VoCodeEnum;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.manager.dto.auth.LoginDTO;
import com.jun.biz.manager.service.AuthService;
import com.jun.biz.manager.vo.auth.AuthVO;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2020/10/23 11:05
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @GetMapping("/captcha")
    public ResultVO<String> captcha(HttpSession session) {
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(97, 38);
        // 获取运算的结果
        String code = captcha.text();
        String img = captcha.toBase64();
        session.setAttribute("validCode", code);
        return new ResultVO<>(VoCodeEnum.SUCCESS, img);
    }

    @PostMapping("/login")
    public ResultVO<Boolean> login(@Validated @RequestBody LoginDTO dto) {
        ResultVO<AuthVO> resultVO = authService.login(dto);
        if (resultVO.isSuccess()) {
            AuthVO authVO = resultVO.getData();
            AdminSessionHolder.setCurrentAdmin(authVO);
            return ResultVO.buildSuccessResult();
        }
        return ResultVO.buildFailResult(resultVO.getMsg());
    }

    @GetMapping("/logout")
    public ResultVO<Boolean> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResultVO.buildSuccessResult();
    }
}

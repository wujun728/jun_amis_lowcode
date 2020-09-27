package com.itstyle.jwt.web;


import com.itstyle.jwt.common.annotation.RequiresRoles;
import com.itstyle.jwt.common.model.Result;
import com.itstyle.jwt.common.util.JwtUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信搜索爪哇妹有惊喜额
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    @RequiresRoles(value="admin")
    public Result list() {
        return Result.ok("十万亿个用户");
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public Result login() {
        /**
         * 模拟登录过程并返回token
         */
        String token = JwtUtils.createJWT("101","爪哇笔记",1000*60*60);
        return Result.ok(token);
    }
}

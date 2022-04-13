package com.element.modules.app.controller;


import com.element.modules.app.annotation.LoginUser;
import com.element.modules.app.entity.UserEntity;
import com.element.common.utils.ResultVo;
import com.element.modules.app.annotation.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public ResultVo userInfo(@LoginUser UserEntity user){
        return ResultVo.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public ResultVo userInfo(@RequestAttribute("userId") Integer userId){
        return ResultVo.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public ResultVo notToken(){
        return ResultVo.ok().put("msg", "无需token也能访问。。。");
    }

}

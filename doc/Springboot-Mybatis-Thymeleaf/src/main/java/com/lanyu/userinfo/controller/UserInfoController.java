package com.lanyu.userinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户个人信息
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserInfoController {
    @RequestMapping(value = "/gotoPage/{page}")
    public String gotoPage(@PathVariable("page") String page){
        return "/user/"+page;
    }
}

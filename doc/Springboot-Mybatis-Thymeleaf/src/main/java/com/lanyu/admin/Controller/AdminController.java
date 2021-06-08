package com.lanyu.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/homepage", produces = {"application/json;charset=UTF-8"})
    public String gotoHome(){
        return "home/index_v4";
    }
}

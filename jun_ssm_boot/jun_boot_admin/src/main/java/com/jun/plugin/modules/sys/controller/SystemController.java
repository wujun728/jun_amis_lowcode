package com.jun.plugin.modules.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author Wujun
 */
@Controller
public class SystemController{
	
    @Autowired
    private RedisCacheManager redisCacheManager;

    /*首页*/
    @RequestMapping(value={"/","/index2"})
    public String index(HttpServletRequest request){
        return "index/index";
    }

	@GetMapping(value = "/icons2")
	public String getIcons2() {
		return "icon/icons2";
	}

    /*注册*/
//    @GetMapping(value = "/register")
//    public String register(){
//        return "system/register";
//    }
 

    /*登陆*/
//    @GetMapping("/login")
//    public String login(Map map){
//        return "system/login";
//    }
 
    /*踢出*/
//    @GetMapping("/kickout")
//    public String kickout(Map map){
//        return "system/kickout";
//    }

    /*图标*/
//    @GetMapping(value = "/icons")
//    public String getIcons(){
//        return "ui/icons";
//    }

    @GetMapping(value = "/test")
    public String test(){
        return "ui/icons";
    }

    @GetMapping(value = "/test1")
    public String test1(){
        return "ui/icons";
    }

}

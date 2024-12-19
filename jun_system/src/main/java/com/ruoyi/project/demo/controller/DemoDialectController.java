package com.ruoyi.project.demo.controller;

import cn.hutool.json.JSONUtil;
import com.ruoyi.project.demo.service.DemoDialectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义Thymeleaf标签示例
 * @author daixf
 */
@Controller
@RequestMapping("/demo/dialect")
public class DemoDialectController {

    @Autowired
    private DemoDialectService demoDialectService;

    private String prefix = "demo/dialect";

    /**
     * 自定义下拉框
     */
    @GetMapping("/select")
    public String select(HttpServletRequest request) {
        request.setAttribute("posts", JSONUtil.toJsonStr(demoDialectService.selectAllPost()));
        return prefix + "/select";
    }

    /**
     * 其他自定义控件
     */
    @GetMapping("/other")
    public String other(HttpServletRequest request) {
        request.setAttribute("posts", JSONUtil.toJsonStr(demoDialectService.selectAllPost()));
        return prefix + "/other";
    }
}

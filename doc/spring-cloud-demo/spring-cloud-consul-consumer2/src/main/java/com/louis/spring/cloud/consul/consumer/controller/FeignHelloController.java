package com.louis.spring.cloud.consul.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.spring.cloud.consul.consumer.service.FeignHelloService;

@RestController
public class FeignHelloController {

    @Autowired
    private FeignHelloService feignHelloService;
    
    @RequestMapping("/feign/call")
    public String call() {
        // 像调用本地服务一样
        return feignHelloService.hello();
    }
}
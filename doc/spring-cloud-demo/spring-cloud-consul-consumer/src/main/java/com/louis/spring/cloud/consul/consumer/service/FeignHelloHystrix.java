package com.louis.spring.cloud.consul.consumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class FeignHelloHystrix implements FeignHelloService {

    @RequestMapping("/hello")
    public String hello() {
    	return "sorry, this service call failed.";
    }
}

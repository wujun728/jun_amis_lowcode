package com.louis.spring.cloud.consul.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-producer", fallback = FeignHelloHystrix.class)
public interface FeignHelloService {

    @RequestMapping("/hello")
    public String hello();
}

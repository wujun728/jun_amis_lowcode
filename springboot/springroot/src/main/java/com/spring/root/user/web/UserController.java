package com.spring.root.user.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@EnableAutoConfiguration
@RequestMapping("/user")  
public class UserController {

    @RequestMapping("/user/{myName}")  
    public String demo(@PathVariable String myName) {  
        return "Hello,"+myName+"!!!";  
    }  
}

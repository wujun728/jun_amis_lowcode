package com.lanyu.common.Controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    private Logger logger = Logger.getLogger(com.lanyu.common.Controller.DemoController.class);

    @RequestMapping(value = "/gotoPage/{page}")
    public String gotoPage(@PathVariable("page") String page){
        return "demo/"+page;
    }
}


package com.jqp.admin.rbac.controller;

import com.jqp.admin.common.Result;
import com.jqp.admin.rbac.service.DynamicTaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dynamicTask")
public class DynamicTaskController {
    @Resource
    DynamicTaskService dynamicTaskService;
    @RequestMapping("/test")
    public Result<Long> test(){
        Map<String,Object> params = new HashMap<>();
        params.put("name","测试动态定时任务");
        Long taskId = dynamicTaskService.save("测试动态定时任务", 1L, new Date(System.currentTimeMillis() + 60 * 1000), params, "/timerTask/testTimerTask",null);
        return Result.success(taskId);
    }
}


package com.jqp.admin.common.controller;

import cn.hutool.json.JSONUtil;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.service.DbTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/admin/template")
@RestController
public class TemplateController {

    @Resource
    private DbTemplateService dbTemplateService;
    @PostMapping("/test/{code}")
    public Result test(@PathVariable String code, @RequestBody Map<String,Object> params){
        String content = (String) params.get("params");
        String value = dbTemplateService.getValue(code, JSONUtil.parseObj(content));
        params.put("value",value);
        return Result.success(params);
    }
}

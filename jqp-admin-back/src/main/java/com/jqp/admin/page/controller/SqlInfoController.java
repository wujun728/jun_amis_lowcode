package com.jqp.admin.page.controller;

import com.jqp.admin.common.Result;
import com.jqp.admin.db.service.SqlInfoService;
import com.jqp.admin.page.data.SqlInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sqlInfo")
@Slf4j
public class SqlInfoController {
    @Resource
    private SqlInfoService sqlInfoService;

    @PostMapping("/resultFields")
    public Result<SqlInfo> resultFields(@RequestBody SqlInfo sqlInfo){
        sqlInfoService.resultFields(sqlInfo);
        return Result.success(sqlInfo);
    }
}

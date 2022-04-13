package com.ruoyi.demo.controller;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * swagger3 用法示例
 *
 * @author Lion Li
 */
@Api(value = "演示swagger3控制器", tags = {"演示swagger3接口"})
@RestController
@RequestMapping("/swagger/demo")
public class Swagger3DemoController {

    /**
     * 上传请求
     * 必须使用 @RequestPart 注解标注为文件
     */
    @ApiOperation(value = "通用上传请求")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "文件", paramType = "query", dataTypeClass = File.class, required = true)
    })
    @PostMapping(value = "/upload")
    public R<String> upload(@RequestPart("file") MultipartFile file) {
        return R.ok("操作成功", file.getOriginalFilename());
    }

}

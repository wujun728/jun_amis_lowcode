package com.element.modules.sys.controller;

import com.element.common.utils.PageUtils;
import com.element.common.utils.ResultVo;
import com.element.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 系统日志
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public ResultVo list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysLogService.listSysLogByPage(params);
        return ResultVo.ok().put("page", page);
    }

}

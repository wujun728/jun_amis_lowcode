package com.element.modules.job.controller;

import com.element.common.annotation.SysLog;
import com.element.common.utils.PageUtils;
import com.element.common.utils.ResultVo;
import com.element.common.validator.ValidatorUtils;
import com.element.modules.job.entity.ScheduleJobEntity;
import com.element.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public ResultVo list(@RequestParam Map<String, Object> params) {
        PageUtils page = scheduleJobService.listJobByPage(params);
        return ResultVo.ok().put("page", page);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public ResultVo info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
        return ResultVo.ok().put("schedule", schedule);
    }

    /**
     * 保存定时任务
     */
    @SysLog("保存定时任务")
    @RequestMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public ResultVo save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.saveJob(scheduleJob);
        return ResultVo.ok();
    }

    /**
     * 修改定时任务
     */
    @SysLog("修改定时任务")
    @RequestMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public ResultVo update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.update(scheduleJob);
        return ResultVo.ok();
    }

    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public ResultVo delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
        return ResultVo.ok();
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @RequestMapping("/run")
    @RequiresPermissions("sys:schedule:update")
    public ResultVo run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return ResultVo.ok();
    }

    /**
     * 暂停定时任务
     */
    @SysLog("暂停定时任务")
    @RequestMapping("/pause")
    @RequiresPermissions("sys:schedule:update")
    public ResultVo pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return ResultVo.ok();
    }

    /**
     * 恢复定时任务
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/resume")
    @RequiresPermissions("sys:schedule:update")
    public ResultVo resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return ResultVo.ok();
    }

}

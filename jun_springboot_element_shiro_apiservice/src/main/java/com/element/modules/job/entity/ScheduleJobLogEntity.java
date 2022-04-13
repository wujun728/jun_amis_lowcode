package com.element.modules.job.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 */
@TableName("schedule_job_log")
@ApiModel(value = "ScheduleJobLogEntity对象", description = "定时任务日志")
public class ScheduleJobLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务日志id")
    @TableId
    private Long logId;

    @ApiModelProperty(value = "任务id")
    @TableField("job_id")
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    @TableField("bean_name")
    private String beanName;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "任务状态    0：成功    1：失败")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "失败信息")
    @TableField("error")
    private String error;

    @ApiModelProperty(value = "耗时(单位：毫秒)")
    @TableField("times")
    private Integer times;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.element.modules.job.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 */
@TableName("schedule_job")
@ApiModel(value = "ScheduleJobEntity对象", description = "定时任务")
public class ScheduleJobEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    @TableId
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    @NotBlank(message = "bean名称不能为空")
    @TableField("bean_name")
    private String beanName;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "cron表达式")
    @NotBlank(message = "cron表达式不能为空")
    @TableField("cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "任务状态  0：正常  1：暂停")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

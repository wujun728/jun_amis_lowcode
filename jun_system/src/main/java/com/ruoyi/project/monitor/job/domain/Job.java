package com.ruoyi.project.monitor.job.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.monitor.job.util.CronUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 定时任务调度信息 sys_job
 * @author ruoyi
 */
public class Job extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @MapRow(column = "job_id", type = RowType.LONG)
    private Long jobId;

    /** 任务名称 */
    @MapRow(column = "job_name", type = RowType.STRING)
    private String jobName;

    /** 任务组名 */
    @MapRow(column = "job_group", type = RowType.STRING)
    private String jobGroup;

    /** 调用目标字符串 */
    @MapRow(column = "invoke_target", type = RowType.STRING)
    private String invokeTarget;

    /** cron执行表达式 */
    @MapRow(column = "cron_expression", type = RowType.STRING)
    private String cronExpression;

    /** cron计划策略 */
    @MapRow(column = "misfire_policy", type = RowType.STRING)
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 是否并发执行（0允许 1禁止） */
    @MapRow(column = "concurrent", type = RowType.STRING)
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    @MapRow(column = "status", type = RowType.STRING)
    private String status;

    public Long getJobId() {
        return jobId;
    }

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@NotBlank(message = "任务名称不能为空")
	@Size(min = 0, max = 64, message = "任务名称不能超过64个字符")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	@NotBlank(message = "调用目标字符串不能为空")
	@Size(min = 0, max = 1000, message = "调用目标字符串长度不能超过500个字符")
	public String getInvokeTarget() {
		return invokeTarget;
	}

	public void setInvokeTarget(String invokeTarget) {
		this.invokeTarget = invokeTarget;
	}

	@NotBlank(message = "Cron执行表达式不能为空")
	@Size(min = 0, max = 255, message = "Cron执行表达式不能超过255个字符")
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getNextValidTime() {
		if (StrUtil.isNotBlank(cronExpression)) {
			return CronUtils.getNextExecution(cronExpression);
		}
		return null;
	}

	public String getMisfirePolicy() {
		return misfirePolicy;
	}

	public void setMisfirePolicy(String misfirePolicy) {
		this.misfirePolicy = misfirePolicy;
	}

	public String getConcurrent() {
		return concurrent;
	}

	public void setConcurrent(String concurrent) {
		this.concurrent = concurrent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("jobId", getJobId())
				.append("jobName", getJobName()).append("jobGroup", getJobGroup())
				.append("cronExpression", getCronExpression()).append("nextValidTime", getNextValidTime())
				.append("misfirePolicy", getMisfirePolicy()).append("concurrent", getConcurrent())
				.append("status", getStatus()).append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
				.toString();
	}
}
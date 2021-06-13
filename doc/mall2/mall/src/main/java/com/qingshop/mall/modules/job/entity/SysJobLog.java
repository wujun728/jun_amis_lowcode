package com.qingshop.mall.modules.job.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定时任务调度日志表
 */
@TableName("sys_job_log")
public class SysJobLog implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 任务日志ID
	 */
	@TableId("job_log_id")
	private Long jobLogId;

	/**
	 * 任务名称
	 */
	@TableField("job_name")
	private String jobName;

	/**
	 * 任务组名
	 */
	@TableField("job_group")
	private String jobGroup;

	/**
	 * 调用目标字符串
	 */
	@TableField("invoke_target")
	private String invokeTarget;

	/**
	 * 日志信息
	 */
	@TableField("job_message")
	private String jobMessage;

	/**
	 * 执行状态（0正常 1失败）
	 */
	private String status;

	/**
	 * 异常信息
	 */
	@TableField("exception_info")
	private String exceptionInfo;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/** 开始时间 */
	@TableField(exist = false)
	private Date startTime;

	/** 结束时间 */
	@TableField(exist = false)
	private Date endTime;

	public Long getJobLogId() {
		return jobLogId;
	}

	public void setJobLogId(Long jobLogId) {
		this.jobLogId = jobLogId;
	}

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

	public String getInvokeTarget() {
		return invokeTarget;
	}

	public void setInvokeTarget(String invokeTarget) {
		this.invokeTarget = invokeTarget;
	}

	public String getJobMessage() {
		return jobMessage;
	}

	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SysJobLog{" + "jobLogId=" + jobLogId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", invokeTarget=" + invokeTarget + ", jobMessage=" + jobMessage + ", status=" + status + ", exceptionInfo=" + exceptionInfo + ", createTime=" + createTime + "}";
	}
}

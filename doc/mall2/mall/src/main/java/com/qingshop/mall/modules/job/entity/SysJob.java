package com.qingshop.mall.modules.job.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定时任务调度表
 */
@TableName("sys_job")
public class SysJob implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务ID
	 */
	@TableId("job_id")
	private Long jobId;

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
	 * cron执行表达式
	 */
	@TableField("cron_expression")
	private String cronExpression;

	/**
	 * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	@TableField("misfire_policy")
	private String misfirePolicy;

	/**
	 * 是否并发执行（0允许 1禁止）
	 */
	private String concurrent;

	/**
	 * 状态（0正常 1暂停）
	 */
	private String status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	/**
	 * 备注信息
	 */
	private String remark;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
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

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysJob{" + "jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", invokeTarget=" + invokeTarget + ", cronExpression=" + cronExpression + ", misfirePolicy=" + misfirePolicy + ", concurrent=" + concurrent + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", remark=" + remark + "}";
	}
}

package com.jun.plugin.modules.job.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	@TableField("concurrent")
	private String concurrent;

	/**
	 * 状态（0正常 1暂停）
	 */
	@TableField("status")
	private String status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 备注信息
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 获取任务ID
	 *
	 * @return job_id - 任务ID
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * 设置任务ID
	 *
	 * @param jobId 任务ID
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * 获取任务名称
	 *
	 * @return job_name - 任务名称
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 设置任务名称
	 *
	 * @param jobName 任务名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取任务组名
	 *
	 * @return job_group - 任务组名
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 设置任务组名
	 *
	 * @param jobGroup 任务组名
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 获取调用目标字符串
	 *
	 * @return invoke_target - 调用目标字符串
	 */
	public String getInvokeTarget() {
		return invokeTarget;
	}

	/**
	 * 设置调用目标字符串
	 *
	 * @param invokeTarget 调用目标字符串
	 */
	public void setInvokeTarget(String invokeTarget) {
		this.invokeTarget = invokeTarget;
	}

	/**
	 * 获取cron执行表达式
	 *
	 * @return cron_expression - cron执行表达式
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 设置cron执行表达式
	 *
	 * @param cronExpression cron执行表达式
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 获取计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 *
	 * @return misfire_policy - 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	public String getMisfirePolicy() {
		return misfirePolicy;
	}

	/**
	 * 设置计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 *
	 * @param misfirePolicy 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
	 */
	public void setMisfirePolicy(String misfirePolicy) {
		this.misfirePolicy = misfirePolicy;
	}

	/**
	 * 获取是否并发执行（0允许 1禁止）
	 *
	 * @return concurrent - 是否并发执行（0允许 1禁止）
	 */
	public String getConcurrent() {
		return concurrent;
	}

	/**
	 * 设置是否并发执行（0允许 1禁止）
	 *
	 * @param concurrent 是否并发执行（0允许 1禁止）
	 */
	public void setConcurrent(String concurrent) {
		this.concurrent = concurrent;
	}

	/**
	 * 获取状态（0正常 1暂停）
	 *
	 * @return status - 状态（0正常 1暂停）
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态（0正常 1暂停）
	 *
	 * @param status 状态（0正常 1暂停）
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取备注信息
	 *
	 * @return remark - 备注信息
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注信息
	 *
	 * @param remark 备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
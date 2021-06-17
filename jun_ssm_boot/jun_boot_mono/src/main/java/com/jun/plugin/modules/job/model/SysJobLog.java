package com.jun.plugin.modules.job.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_job_log")
public class SysJobLog {
    /**
     * 任务日志ID
     */
    @Id
    @Column(name = "job_log_id")
    private Long jobLogId;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务组名
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    @Column(name = "invoke_target")
    private String invokeTarget;

    /**
     * 日志信息
     */
    @Column(name = "job_message")
    private String jobMessage;

    /**
     * 执行状态（0正常 1失败）
     */
    private String status;

    /**
     * 异常信息
     */
    @Column(name = "exception_info")
    private String exceptionInfo;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    
    /** 开始时间 */
    @Transient
	private Date startTime;

	/** 结束时间 */
    @Transient
	private Date endTime;

    /**
     * 获取任务日志ID
     *
     * @return job_log_id - 任务日志ID
     */
    public Long getJobLogId() {
        return jobLogId;
    }

    /**
     * 设置任务日志ID
     *
     * @param jobLogId 任务日志ID
     */
    public void setJobLogId(Long jobLogId) {
        this.jobLogId = jobLogId;
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
     * 获取日志信息
     *
     * @return job_message - 日志信息
     */
    public String getJobMessage() {
        return jobMessage;
    }

    /**
     * 设置日志信息
     *
     * @param jobMessage 日志信息
     */
    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    /**
     * 获取执行状态（0正常 1失败）
     *
     * @return status - 执行状态（0正常 1失败）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置执行状态（0正常 1失败）
     *
     * @param status 执行状态（0正常 1失败）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取异常信息
     *
     * @return exception_info - 异常信息
     */
    public String getExceptionInfo() {
        return exceptionInfo;
    }

    /**
     * 设置异常信息
     *
     * @param exceptionInfo 异常信息
     */
    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
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
    
}
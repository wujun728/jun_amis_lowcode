package com.jun.plugin.ow.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.jun.plugin.common.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 招贤纳士表 ow_jobs
 * 
 * @author admin
 * @date 2018-12-12
 */
@Table(name = "ow_jobs")
public class Jobs extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	private Long id;
	/** 职位名字 */
	private String jobName;
	/** 工作经验 */
	private String workExperience;
	/** 招聘人数 */
	private Integer jobNumber;
	/** 薪资待遇 */
	private String salary;
	/** 备注 */
	private String remark;
	/** 状态（0正常 1关闭） */
	private String status;
	/** 招聘详细内容 */
	private String content;
	/** 学历 */
	private String education;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getJobName() 
	{
		return jobName;
	}
	public void setWorkExperience(String workExperience) 
	{
		this.workExperience = workExperience;
	}

	public String getWorkExperience() 
	{
		return workExperience;
	}
	public void setJobNumber(Integer jobNumber) 
	{
		this.jobNumber = jobNumber;
	}

	public Integer getJobNumber() 
	{
		return jobNumber;
	}
	public void setSalary(String salary) 
	{
		this.salary = salary;
	}

	public String getSalary() 
	{
		return salary;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setEducation(String education) 
	{
		this.education = education;
	}

	public String getEducation() 
	{
		return education;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jobName", getJobName())
            .append("workExperience", getWorkExperience())
            .append("jobNumber", getJobNumber())
            .append("salary", getSalary())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("content", getContent())
            .append("education", getEducation())
            .toString();
    }
}

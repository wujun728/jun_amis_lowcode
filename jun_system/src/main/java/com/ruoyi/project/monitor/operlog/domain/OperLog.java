package com.ruoyi.project.monitor.operlog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 操作日志记录表 oper_log
 * @author ruoyi
 */
public class OperLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 日志主键 */
	private Long operId;

	/** 操作模块 */
	private String title;

	/** 业务类型 */
	private Integer businessType;

	/** 业务类型数组 */
	private Integer[] businessTypes;

	/** 请求方法 */
	private String method;

	/** 请求方式 */
	private String requestMethod;

	/** 操作人类别 */
	private Integer operatorType;

	/** 操作人员 */
	private String operName;

	/** 部门名称 */
	private String deptName;

	/** 请求url */
	private String operUrl;

	/** 操作地址 */
	private String operIp;

	/** 操作地点 */
	private String operLocation;

	/** 请求参数 */
	private String operParam;

	/** 返回参数 */
	private String jsonResult;

	/** 状态0正常 1异常 */
	private Integer status;

	/** 错误消息 */
	private String errorMsg;

	/** 操作时间 */
	private Date operTime;

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer[] getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(Integer[] businessTypes) {
		this.businessTypes = businessTypes;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getOperLocation() {
		return operLocation;
	}

	public void setOperLocation(String operLocation) {
		this.operLocation = operLocation;
	}

	public String getOperParam() {
		return operParam;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("operId", getOperId())
				.append("title", getTitle()).append("businessType", getBusinessType())
				.append("businessTypes", getBusinessTypes()).append("method", getMethod())
				.append("requestMethod", getRequestMethod()).append("operatorType", getOperatorType())
				.append("operName", getOperName()).append("deptName", getDeptName()).append("operUrl", getOperUrl())
				.append("operIp", getOperIp()).append("operLocation", getOperLocation())
				.append("operParam", getOperParam()).append("status", getStatus()).append("errorMsg", getErrorMsg())
				.append("operTime", getOperTime()).toString();
	}
}
package com.ruoyi.project.monitor.online.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.monitor.online.domain.OnlineSession.OnlineStatus;

/**
 * 当前在线会话 sys_user_online
 * @author ruoyi
 */
public class UserOnline extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 用户会话id */
	@MapRow(column = "sessionId", type = RowType.STRING)
	private String sessionId;

	/** 部门名称 */
	@MapRow(column = "dept_name", type = RowType.STRING)
	private String deptName;

	/** 登录名称 */
	@MapRow(column = "login_name", type = RowType.STRING)
	private String loginName;

	/** 登录IP地址 */
	@MapRow(column = "ipaddr", type = RowType.STRING)
	private String ipaddr;

	/** 登录地址 */
	@MapRow(column = "login_location", type = RowType.STRING)
	private String loginLocation;

	/** 浏览器类型 */
	@MapRow(column = "browser", type = RowType.STRING)
	private String browser;

	/** 操作系统 */
	@MapRow(column = "os", type = RowType.STRING)
	private String os;

	/** session创建时间 */
	@MapRow(column = "start_timestamp", type = RowType.DATE)
	private Date startTimestamp;

	/** session最后访问时间 */
	@MapRow(column = "last_access_time", type = RowType.DATE)
	private Date lastAccessTime;

	/** 超时时间，单位为分钟 */
	@MapRow(column = "expire_time", type = RowType.LONG)
	private Long expireTime;

	/** 在线状态 */
	@MapRow(column = "status", type = RowType.STRING)
	private String onlineStatus;

	/** 在线状态 */
	private OnlineStatus status = OnlineStatus.on_line;

	/** 备份的当前用户会话 */
	private OnlineSession session;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public OnlineSession getSession() {
		return session;
	}

	public void setSession(OnlineSession session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("sessionId", getSessionId())
				.append("loginName", getLoginName()).append("deptName", getDeptName()).append("ipaddr", getIpaddr())
				.append("loginLocation", getLoginLocation()).append("browser", getBrowser()).append("os", getOs())
				.append("status", getStatus()).append("startTimestamp", getStartTimestamp())
				.append("lastAccessTime", getLastAccessTime()).append("expireTime", getExpireTime()).toString();
	}
}
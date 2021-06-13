package com.qingshop.mall.modules.system.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * 邮件记录表
 */
public class SysMail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("mail_id")
	private Long mailId;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 内容
	 */
	@TableField("content")
	private String content;

	/**
	 * 发送者邮箱
	 */
	@TableField("from_mail")
	private String fromMail;

	/**
	 * 目标者邮箱
	 */
	@TableField("to_mail")
	private String toMail;

	/**
	 * 状态 1 成功 0 失败
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	@Override
	public String toString() {
		return "SysMail{" + "mailId=" + mailId + ", title=" + title + ", content=" + content + ", formMail=" + fromMail + ", toMail=" + toMail + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}

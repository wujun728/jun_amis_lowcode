package com.qingshop.mall.modules.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 文件存储分组表
 */
@TableName("sys_upload_group")
public class SysUploadGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId(value = "group_id", type = IdType.INPUT)
	private Long groupId;
	/**
	 * 文件类型
	 */
	@TableField("group_type")
	private String groupType;
	/**
	 * 分类名称
	 */
	@TableField("group_name")
	private String groupName;
	/**
	 * 分类排序(数字越小越靠前)
	 */
	private Integer sort;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
		return "SysUploadGroup{" + "groupId=" + groupId + ", groupType=" + groupType + ", groupName=" + groupName + ", sort=" + sort + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}

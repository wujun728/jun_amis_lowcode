package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 类目表
 */
@TableName("mall_category")
public class MallCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("category_id")
	private Long categoryId;

	/**
	 * 父类目ID
	 */
	@TableField("parent_id")
	private Long parentId;

	/**
	 * 祖级列表
	 */
	private String ancestors;

	/**
	 * 类目名称
	 */
	private String name;

	/**
	 * 类目广告语介绍
	 */
	private String descripte;

	/**
	 * 类目图片
	 */
	@TableField("pic_url")
	private String picUrl;

	/**
	 * 状态（0停用 1正常）
	 */
	private String status;

	/**
	 * 排序
	 */
	@TableField("sort_order")
	private Integer sortOrder;

	/**
	 * 逻辑删除
	 */
	private Integer deleted;

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

	/**
	 * 子节点（非数据库映射字段）
	 */
	@TableField(exist = false)
	private List<MallCategory> children;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAncestors() {
		return ancestors;
	}

	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripte() {
		return descripte;
	}

	public void setDescripte(String descripte) {
		this.descripte = descripte;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public List<MallCategory> getChildren() {
		return children;
	}

	public void setChildren(List<MallCategory> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "MallCategory{" + "categoryId=" + categoryId + ", parentId=" + parentId + ", ancestors=" + ancestors + ", name=" + name + ", descripte=" + descripte + ", picUrl=" + picUrl + ", status=" + status + ", sortOrder=" + sortOrder + ", deleted=" + deleted + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "}";
	}
}

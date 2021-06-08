package com.qingshop.mall.modules.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingshop.mall.framework.annotation.Excel;
import com.qingshop.mall.framework.annotation.Excel.Type;

/**
 * 部门表
 */
@TableName("sys_dept")
public class SysDept implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("dept_id")
	@Excel(name = "部门编号", type = Type.IMPORT)
	private Long deptId;

	/**
	 * 部门名称
	 */
	@TableField("dept_name")
	@Excel(name = "部门名称")
	private String deptName;

	/**
	 * 描述
	 */
	@TableField("dept_desc")
	@Excel(name = "部门描述")
	private String deptDesc;

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

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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

}

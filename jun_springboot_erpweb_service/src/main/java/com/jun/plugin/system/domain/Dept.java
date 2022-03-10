package com.jun.plugin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/17
 *
 * 
 * 
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dept")
public class Dept implements Serializable {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 父级部门ID
	 */
	@TableField(value = "pid")
	private Integer pid;

	/**
	 * 部门名称
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 是否展开0不展开1展开
	 */
	@TableField(value = "spread")
	private Integer spread;

	/**
	 * 前端TreeTable open是否展开 TableField(exist = false) 不存在处理一下 表示 数据库当中没有这个 字段 让mq 忽略
	 */
	/*
	 * @TableField(exist = false) private Boolean open;
	 */

	/**
	 * 备注
	 */
	@TableField(value = "remark")
	private String remark;

	/**
	 * 地址
	 */
	@TableField(value = "address")
	private String address;

	/**
	 * 状态【0不可用1可用】
	 */
	@TableField(value = "available")
	private Integer available;

	/**
	 * 排序码【为了调事显示顺序】
	 */
	@TableField(value = "ordernum")
	private Integer ordernum;

	/**
	 * 创建时间
	 */
	@TableField(value = "createtime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	/**
	 * 重写判断 前端table 是否展开
	 *
	 * @return
	 */
//	  public Boolean getOpen() {
//			return this.spread == 1 ? true : false;
//	  }

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_PID = "pid";

	public static final String COL_TITLE = "title";

	public static final String COL_SPREAD = "spread";

	public static final String COL_REMARK = "remark";

	public static final String COL_ADDRESS = "address";

	public static final String COL_AVAILABLE = "available";

	public static final String COL_ORDERNUM = "ordernum";

	public static final String COL_CREATETIME = "createtime";
}
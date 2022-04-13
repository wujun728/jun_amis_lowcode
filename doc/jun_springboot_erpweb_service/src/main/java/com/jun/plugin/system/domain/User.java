package com.jun.plugin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * ClassName: User Description: layui date: 2020/4/14 19:50
 *
 * 
 * 
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 姓名
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 登陆名
	 */
	@TableField(value = "loginname")
	private String loginname;

	/**
	 * 地址
	 */
	@TableField(value = "address")
	private String address;

	/**
	 * 性别
	 */
	@TableField(value = "sex")
	private Integer sex;

	/**
	 * 备注
	 */
	@TableField(value = "remark")
	private String remark;

	/**
	 * 密码
	 */
	@TableField(value = "pwd")
	@JsonIgnore // 生成json串时 不进行序列化 也就是 json串当中 没有这个字段
	private String pwd;

	/**
	 * 部门ID
	 */
	@TableField(value = "deptid")
	private Integer deptid;

	/**
	 * 部门名称 表示新增字段 mybatisplush不进行 使用该字段
	 */
	@TableField(exist = false)
	private String deptname;

	/**
	 * qq邮箱验证码
	 */
	@TableField(exist = false)
	private String mailEmail;

	/**
	 * 入职时间
	 */
	@TableField(value = "hiredate")
	// 接收前台
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// 后台返回前台
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date hiredate;

	@TableField(value = "ordernum")
	private Integer ordernum;

	/**
	 * 用户类型[0超级管理员1普通用户]
	 */
	@TableField(value = "type")
	private Integer type;

	/**
	 * 头像地址
	 */
	@TableField(value = "imgpath")
	private String imgpath;

	/**
	 * 盐
	 */
	@TableField(value = "salt")
	@JsonIgnore
	private String salt;

	/**
	 * 是否可用
	 */
	@TableField(value = "available")
	private Integer available;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_NAME = "name";

	public static final String COL_LOGINNAME = "loginname";

	public static final String COL_ADDRESS = "address";

	public static final String COL_SEX = "sex";

	public static final String COL_REMARK = "remark";

	public static final String COL_PWD = "pwd";

	public static final String COL_DEPTID = "deptid";

	public static final String COL_HIREDATE = "hiredate";

	public static final String COL_ORDERNUM = "ordernum";

	public static final String COL_TYPE = "type";

	public static final String COL_IMGPATH = "imgpath";

	public static final String COL_SALT = "salt";

	public static final String COL_AVAILABLE = "available";
}
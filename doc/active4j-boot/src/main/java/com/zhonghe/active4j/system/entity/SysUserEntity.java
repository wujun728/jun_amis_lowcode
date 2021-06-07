package com.zhonghe.active4j.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhonghe.active4j.common.entity.BaseEntity;
import com.zhonghe.active4j.core.annotation.QueryField;
import com.zhonghe.active4j.core.model.QueryCondition;

import lombok.Getter;
import lombok.Setter;


/**
 * 系统管理	用户管理
 * @author teli_
 *
 */
@TableName("sys_user")
@Getter
@Setter
public class SysUserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7348020772533478062L;

	/**
	 * 用户名
	 */
	@TableField("USER_NAME")
	@QueryField(queryColumn="USER_NAME", condition=QueryCondition.eq)
	private String userName;
	
	/**
	 * 真实姓名
	 */
	@TableField("REAL_NAME")
	@QueryField(queryColumn="REAL_NAME", condition=QueryCondition.like)
	private String realName;
	
	/**
	 *  密码
	 */
	@TableField("PASSWORD")
	private String password;
	
	/**
	 * 盐
	 */
	@TableField("SALT")
	private String salt;
	
	/**
	 * 头像
	 */
	@TableField("HEAD_IMG_URL")
	private String headImgUrl;
	
	/**
	 * 用户状态
	 */
	@TableField("STATUS")
	@QueryField(queryColumn="STATUS", condition=QueryCondition.eq)
	private String status;
	
	/**
	 * 性别
	 */
	@TableField("SEX")
	private String sex;
	
	/**
	 * 所属部门
	 */
	@TableField("DEPT_ID")
	@QueryField(queryColumn="DEPT_ID", condition=QueryCondition.eq)
	private String deptId;
	
	/**
	 * 电子邮箱
	 */
	@TableField("EMAIL")
	private String email;
	
	/**
	 * 手机号
	 */
	@TableField("TEL_NO")
	private String telNo;
	
	/**
	 * 用户编号
	 */
	@TableField("USER_NO")
	private String userNo;
	
	/**
	 * 备注
	 */
	@TableField("MEMO")
	private String memo;
	
	
}

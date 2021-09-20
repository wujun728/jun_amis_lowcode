package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 客户信息
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-09-19 08:27:14
 */
@Data
@TableName("biz_customer")
public class BizCustomerEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("id")
	private Integer id;

	/**
	 * 客户名称
	 */
	@TableField("cusname")
	private String cusname;

	/**
	 * 客户描述
	 */
	@TableField("cusdesc")
	private String cusdesc;

	/**
	 * 客户全称
	 */
	@TableField("fullname")
	private String fullname;

	/**
	 * 客户性质
	 */
	@TableField("cussex")
	private String cussex;

	/**
	 * 注册时间
	 */
	@TableField("register_date")
	private Date registerDate;

	/**
	 * 客户类型
	 */
	@TableField("custype")
	private String custype;


}
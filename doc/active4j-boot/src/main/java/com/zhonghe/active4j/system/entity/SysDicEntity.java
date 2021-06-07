package com.zhonghe.active4j.system.entity;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhonghe.active4j.common.entity.BaseEntity;
import com.zhonghe.active4j.core.annotation.QueryField;
import com.zhonghe.active4j.core.model.QueryCondition;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典表
 * @author teli_
 *
 */
@TableName("sys_dic")
@Getter
@Setter
public class SysDicEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7516587511068473316L;

	/**
	 * 字典代码
	 */
	@TableField("CODE")
	@NotEmpty(message="字典编码不能为空")
	private String code;
	
	/**
	 * 字段名称
	 */
	@TableField("NAME")
	@QueryField(condition=QueryCondition.like, queryColumn="NAME")
	@NotEmpty(message="字典值不能为空")
	private String name;
	
	/**
	 * 备注信息
	 */
	@TableField("MEMO")
	private String memo;
	
}

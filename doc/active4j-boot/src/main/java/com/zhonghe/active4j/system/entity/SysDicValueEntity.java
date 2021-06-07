package com.zhonghe.active4j.system.entity;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhonghe.active4j.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典值
 * @author teli_
 *
 */
@TableName("sys_dic_value")
@Getter
@Setter
public class SysDicValueEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184574569285002609L;

	/**
	 * 显示值
	 */
	@TableField("LABEL")
	@NotEmpty(message="名称不能为空")
	private String label;
	
	/**
	 * 值
	 */
	@TableField("VALUE")
	@NotEmpty(message="值不能为空")
	private String value;
	
	/**
	 * 所属字典
	 */
	@TableField("PARENT_ID")
	private String parentId;
	
}

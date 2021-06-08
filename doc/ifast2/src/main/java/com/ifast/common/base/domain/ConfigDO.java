package com.ifast.common.base.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * @author Aron
 * @email izenglong@163.com
 * @date 2018-04-06 01:05:22
 */
@TableName("sys_config")
@Data
public class ConfigDO extends Model<ConfigDO> implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableId
	private Long    id;
	private String  k;
	private String  v;
	private String  remark;
	@TableField("create_time")
	private Date    createTime;
	@TableField("kv_type")
	private Integer kvType;

	@Override
	protected Serializable pkVal() {
		return id;
	}
}

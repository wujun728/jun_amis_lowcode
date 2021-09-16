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

/**
 * ClassName: Loginfo Description: layui date: 2020/4/19
 * 
 * 
 * @since JDK 1.8
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class Role implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "name")
	private String name;

	@TableField(value = "remark")
	private String remark;

	@TableField(value = "available")
	private Integer available;

	@TableField(value = "createtime")
	private Date createtime;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_NAME = "name";

	public static final String COL_REMARK = "remark";

	public static final String COL_AVAILABLE = "available";

	public static final String COL_CREATETIME = "createtime";
}
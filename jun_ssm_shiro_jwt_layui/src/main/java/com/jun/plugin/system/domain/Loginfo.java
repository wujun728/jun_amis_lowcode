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
 * ClassName: Loginfo Description: layui date: 2020/4/15 19:10
 * 
 * 
 * @version
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_loginfo")
public class Loginfo implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "loginname")
	private String loginname;

	@TableField(value = "loginip")
	private String loginip;

	@TableField(value = "logintime")
	private Date logintime;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_LOGINNAME = "loginname";

	public static final String COL_LOGINIP = "loginip";

	public static final String COL_LOGINTIME = "logintime";
}
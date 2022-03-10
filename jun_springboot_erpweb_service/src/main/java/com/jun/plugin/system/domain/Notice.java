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
 * ClassName: Notice Description: layui date: 2020/4/16 15:51
 * 
 * 
 * @version
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_notice")
public class Notice implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "title")
	private String title;

	@TableField(value = "content")
	private String content;

	@TableField(value = "createtime")
	private Date createtime;

	@TableField(value = "opername")
	private String opername;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_TITLE = "title";

	public static final String COL_CONTENT = "content";

	public static final String COL_CREATETIME = "createtime";

	public static final String COL_OPERNAME = "opername";
}
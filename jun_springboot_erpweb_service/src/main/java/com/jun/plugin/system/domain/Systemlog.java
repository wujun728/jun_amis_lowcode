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
 * ClassName: Systemlog Description: layui date: 2020/4/16 17:55
 *
 * 
 * 
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "systemlog")
public class Systemlog implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField(value = "thisName")
	private String thisname;

	@TableField(value = "optime")
	private Date optime;

	@TableField(value = "ip")
	private String ip;

	@TableField(value = "address")
	private String address;

	@TableField(value = "function")
	private String function;

	@TableField(value = "params")
	private String params;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_THISNAME = "thisName";

	public static final String COL_OPTIME = "optime";

	public static final String COL_IP = "ip";

	public static final String COL_ADDRESS = "address";

	public static final String COL_FUNCTION = "function";

	public static final String COL_PARAMS = "params";
}
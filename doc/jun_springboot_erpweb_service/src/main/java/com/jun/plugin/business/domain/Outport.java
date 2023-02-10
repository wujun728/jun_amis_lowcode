package com.jun.plugin.business.domain;

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
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8 商品退货
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_outport")
public class Outport implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "providerid")
	private Integer providerid;

	// 让mybatisplaus忽略
	@TableField(exist = false)
	private String providername;

	@TableField(value = "paytype")
	private String paytype;

	@TableField(value = "outporttime")
	private Date outporttime;

	@TableField(value = "operateperson")
	private String operateperson;

	@TableField(value = "outportprice")
	private Double outportprice;

	@TableField(value = "number")
	private Integer number;

	@TableField(value = "remark")
	private String remark;

	@TableField(value = "goodsid")
	private Integer goodsid;

	@TableField(exist = false)
	private String goodsname;

	@TableField(exist = false)
	private String size;

	@TableField(value = "inportid")
	private Integer inportid;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_PROVIDERID = "providerid";

	public static final String COL_PAYTYPE = "paytype";

	public static final String COL_OUTPORTTIME = "outporttime";

	public static final String COL_OPERATEPERSON = "operateperson";

	public static final String COL_OUTPORTPRICE = "outportprice";

	public static final String COL_NUMBER = "number";

	public static final String COL_REMARK = "remark";

	public static final String COL_GOODSID = "goodsid";

	public static final String COL_INPORTID = "inportid";
}
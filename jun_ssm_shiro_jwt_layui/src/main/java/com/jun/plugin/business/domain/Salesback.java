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
 * @since JDK 1.8 销售 退货
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_salesback")
public class Salesback implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "customerid")
	private Integer customerid;

	@TableField(exist = false)
	private String customername;

	@TableField(value = "paytype")
	private String paytype;

	@TableField(value = "salesbacktime")
	private Date salesbacktime;

	@TableField(value = "salebackprice")
	private Double salebackprice;

	@TableField(value = "operateperson")
	private String operateperson;

	@TableField(value = "number")
	private Integer number;

	@TableField(value = "remark")
	private String remark;

	@TableField(value = "salesid")
	private Integer salesid;

	@TableField(value = "goodsid")
	private Integer goodsid;

	@TableField(exist = false)
	private String goodsname;

	@TableField(exist = false)
	private String size;

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_CUSTOMERID = "customerid";

	public static final String COL_PAYTYPE = "paytype";

	public static final String COL_SALESBACKTIME = "salesbacktime";

	public static final String COL_SALEBACKPRICE = "salebackprice";

	public static final String COL_OPERATEPERSON = "operateperson";

	public static final String COL_NUMBER = "number";

	public static final String COL_REMARK = "remark";

	public static final String COL_SALESID = "salesid";

	public static final String COL_GOODSID = "goodsid";
}
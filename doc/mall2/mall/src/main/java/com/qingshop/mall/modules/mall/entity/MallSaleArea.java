package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 销售区域表
 */
@TableName("mall_sale_area")
public class MallSaleArea implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId("sale_area_id")
	private Long saleAreaId;

	/**
	 * 销售区域省市名称
	 */
	@TableField("sale_area")
	private String saleArea;

	/**
	 * 销售区域(城市id集)
	 */
	@TableField("sale_ids")
	private String saleIds;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public Long getSaleAreaId() {
		return saleAreaId;
	}

	public void setSaleAreaId(Long saleAreaId) {
		this.saleAreaId = saleAreaId;
	}

	public String getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}

	public String getSaleIds() {
		return saleIds;
	}

	public void setSaleIds(String saleIds) {
		this.saleIds = saleIds;
	}

	@Override
	public String toString() {
		return "MallSaleArea{" + "saleAreaId=" + saleAreaId + ", saleArea=" + saleArea + ", saleIds=" + saleIds + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}

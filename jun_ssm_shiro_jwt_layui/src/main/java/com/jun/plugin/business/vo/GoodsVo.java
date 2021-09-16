package com.jun.plugin.business.vo;

import com.jun.plugin.system.vo.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends BaseVo {

	/**
	 * 供应商ID
	 */
	private Integer providerid;
	/**
	 * 商品名称
	 */
	private String goodsname;
	/**
	 * 规格
	 */
	private String size;
	/**
	 * 生产批号
	 */
	private String productcode;
	/**
	 * 批准文号
	 */
	private String promitcode;
	/**
	 * 备注信息
	 */
	private String description;

}

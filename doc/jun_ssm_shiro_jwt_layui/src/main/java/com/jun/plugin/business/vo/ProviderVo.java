package com.jun.plugin.business.vo;

import com.jun.plugin.system.vo.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * 
 * 
 * @since JDK 1.8 公告增强类 模糊查询
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends BaseVo {

	/**
	 * 供应商名称
	 */
	private String customername;
	/**
	 * 供应商电话
	 */
	private String phone;
	/**
	 * 供应商联系人
	 */
	private String connectionperson;

}
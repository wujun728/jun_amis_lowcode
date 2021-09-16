package com.jun.plugin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Salesback;
import com.jun.plugin.business.service.SalesbackService;
import com.jun.plugin.business.vo.SalesbackVo;
import com.jun.plugin.system.common.ResultObj;

/**
 * 
 * 
 * ClassName: SalesbackController create: 2020-04-27 14:37
 *
 * @author: Wujun @since： JDK1.8
 *
 *          退货
 **/

@RestController
@RequestMapping("api/salesback")
public class SalesbackController {

	@Autowired
	private SalesbackService salesbackService;

	/**
	 * 查询商品退货
	 */
	@RequestMapping("loadAllSalesback")
	public Object loadAllSalesback(SalesbackVo salesbackVo) {
		return this.salesbackService.queryAllSalesback(salesbackVo);
	}

	/**
	 * 添加退货信息
	 */
	@RequestMapping("addSalesback")
	public ResultObj addSalesback(Salesback salesback) {
		try {
			this.salesbackService.saveSalesback(salesback);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}

}

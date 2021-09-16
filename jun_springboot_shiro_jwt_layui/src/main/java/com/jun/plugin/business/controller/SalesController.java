package com.jun.plugin.business.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Sales;
import com.jun.plugin.business.service.SalesService;
import com.jun.plugin.business.vo.SalesVo;
import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.ResultObj;

import java.util.Date;

/**
 * ClassName: SalesController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	/**
	 * 查询所有商品销售数据
	 *
	 * @param salesVo
	 * @return
	 */
	@RequestMapping("loadAllSales")
	public Object loadAllSales(SalesVo salesVo) {
		return this.salesService.queryAllSales(salesVo);
	}

	/**
	 * 添加商品销售
	 *
	 * @param sales
	 * @return
	 */
	@RequestMapping("addSales")
	public Object addSales(Sales sales) {
		try {
			ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
			sales.setOperateperson(activeUser.getUser().getName());
			sales.setSalestime(new Date());
			this.salesService.saveSales(sales);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改商品销售
	 *
	 * @param sales
	 * @return
	 */
	@RequestMapping("updateSales")
	public Object updateSales(Sales sales) {
		try {
			this.salesService.updateSales(sales);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除商品销售
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteSales")
	public Object deleteSales(Integer id) {
		try {
			this.salesService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}
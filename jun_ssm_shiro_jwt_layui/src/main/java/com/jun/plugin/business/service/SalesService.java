package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Sales;
import com.jun.plugin.business.vo.SalesVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface SalesService extends IService<Sales> {

	/**
	 * 查询全部商品销售信息
	 *
	 * @param salesVo
	 * @return
	 */
	DataGridView queryAllSales(SalesVo salesVo);

	Sales saveSales(Sales sales);

	Sales updateSales(Sales sales);
}

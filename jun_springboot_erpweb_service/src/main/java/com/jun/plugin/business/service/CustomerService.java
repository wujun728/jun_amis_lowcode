package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.vo.CustomerVo;
import com.jun.plugin.system.common.DataGridView;

import javax.xml.crypto.Data;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/25
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface CustomerService extends IService<Customer> {

	/**
	 * 查询全部联系人
	 *
	 * @param customerVo
	 * @return
	 */
	DataGridView queryAllCustomer(CustomerVo customerVo);

	/**
	 * 保存 客户
	 *
	 * @param customer
	 * @return
	 */
	Customer saveCustomer(Customer customer);

	/**
	 * 更新客户
	 *
	 * @param customer
	 * @return
	 */
	Customer updateCustomer(Customer customer);

	/**
	 * 查询所有可用 的客户
	 * 
	 * @return
	 */
	DataGridView getAllAvailableCustomer();
}

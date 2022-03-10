package com.jun.plugin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.service.CustomerService;
import com.jun.plugin.business.vo.CustomerVo;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.common.WebUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * ClassName: CustomerController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 客户数据
	 *
	 * @param customerVo
	 * @return
	 */
	@RequestMapping("loadAllCustomer")
	public Object loadAllCustomer(CustomerVo customerVo) {
		return this.customerService.queryAllCustomer(customerVo);
	}

	/**
	 * 添加客户
	 *
	 * @param customer
	 * @return
	 */
	@RequestMapping("addCustomer")
	public Object addCustomer(Customer customer) {
		try {
			customer.setAvailable(Constant.AVAILABLE_TRUE);
			this.customerService.saveCustomer(customer);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

//    getAllAvailableCustomer
	@GetMapping("getAllAvailableCustomer")
	public Object getAllAvailableCustomer() {
		return this.customerService.getAllAvailableCustomer();
	}

	/**
	 * 修改客户
	 *
	 * @param customer
	 * @return
	 */
	@RequestMapping("updateCustomer")
	public Object updateCustomer(Customer customer) {
		try {
			this.customerService.updateCustomer(customer);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除客户
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteCustomer")
	public Object deleteCustomer(Integer id) {
		try {
			this.customerService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除客户
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDeleteCustomer")
	public Object batchDeleteCustomer(Integer[] ids) {
		try {
			this.customerService.removeByIds(Arrays.asList(ids));
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}
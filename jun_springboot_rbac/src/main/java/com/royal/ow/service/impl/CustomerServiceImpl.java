package com.royal.ow.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.royal.ow.mapper.CustomerMapper;
import com.royal.ow.domain.Customer;
import com.royal.ow.service.ICustomerService;
import com.royal.common.support.Convert;

/**
 * 客户 服务层实现
 * 
 * @author royal
 * @date 2021-09-25
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
	@Autowired
	private CustomerMapper customerMapper;

	/**
     * 查询客户信息
     * 
     * @param id 客户ID
     * @return 客户信息
     */
    @Override
	public Customer selectCustomerById(Integer id)
	{
	    return customerMapper.selectCustomerById(id);
	}
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	@Override
	public List<Customer> selectCustomerList(Customer customer)
	{
	    return customerMapper.selectCustomerList(customer);
	}

    /**
     * 查询客户列表for 官网
     *
     * @param customer 客户信息
     * @return 客户集合
     */
    @Override
    public List<Customer> selectCustomerListForOw(Customer customer) {
//		customer.setStatus("0");
        return customerMapper.selectCustomerListForOw(customer);
    }

    /**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	@Override
	public int insertCustomer(Customer customer)
	{
	    return customerMapper.insertCustomer(customer);
	}
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	@Override
	public int updateCustomer(Customer customer)
	{
	    return customerMapper.updateCustomer(customer);
	}

	/**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCustomerByIds(String ids)
	{
		return customerMapper.deleteCustomerByIds(Convert.toStrArray(ids));
	}
    /**
     * 通过ID查详情
     *
     * @param id
     * @return Customer
     */
    @Override
    public Customer getCustomerByIds(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }
}

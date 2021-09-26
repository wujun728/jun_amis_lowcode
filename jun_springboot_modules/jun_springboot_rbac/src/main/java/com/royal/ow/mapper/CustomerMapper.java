package com.royal.ow.mapper;

import com.royal.ow.domain.Customer;
import java.util.List;
import com.royal.common.base.BaseMapper;

/**
 * 客户 数据层
 * 
 * @author royal
 * @date 2021-09-25
 */
public interface CustomerMapper extends BaseMapper<Customer>
{
	/**
     * 查询客户信息
     * 
     * @param id 客户ID
     * @return 客户信息
     */
	public Customer selectCustomerById(Integer id);
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	public List<Customer> selectCustomerList(Customer customer);
    /**
     * 查询客户列表 for官网
     *
     * @param customer 客户信息
     * @return 客户集合
     */
    public List<Customer> selectCustomerListForOw(Customer customer);
	/**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int insertCustomer(Customer customer);
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int updateCustomer(Customer customer);
	
	/**
     * 删除客户
     * 
     * @param id 客户ID
     * @return 结果
     */
	public int deleteCustomerById(Integer id);
	
	/**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String[] ids);
	
}
package com.royal.ow.service;

import com.royal.ow.domain.Customer;
import java.util.List;

/**
 * 客户 服务层
 * 
 * @author royal
 * @date 2021-09-25
 */
public interface ICustomerService 
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
     * 查询客户列表for官网
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
     * 删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String ids);

    /**
     * 通过ID查看详情
     * @param id
     * @return
     */
    public Customer getCustomerByIds(Long id);
	
}

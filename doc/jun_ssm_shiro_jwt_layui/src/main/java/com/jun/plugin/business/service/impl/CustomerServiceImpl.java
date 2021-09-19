package com.jun.plugin.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.mapper.CustomerMapper;
import com.jun.plugin.business.service.CustomerService;
import com.jun.plugin.business.vo.CustomerVo;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/25
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 查询全部联系人
	 *
	 * @param customerVo
	 * @return
	 */
	@Override
	public DataGridView queryAllCustomer(CustomerVo customerVo) {
		IPage<Customer> page = new Page<>(customerVo.getPage(), customerVo.getLimit());
		QueryWrapper<Customer> qw = new QueryWrapper<>();
		qw.eq(customerVo.getAvailable() != null, "available", customerVo.getAvailable());
		qw.like(StringUtils.isNotBlank(customerVo.getCustomername()), "customername", customerVo.getCustomername());
		qw.like(StringUtils.isNotBlank(customerVo.getConnectionperson()), "connectionperson",
				customerVo.getConnectionperson());
		// 进行判断一下 要不然为 null
		if (StringUtils.isNotBlank(customerVo.getPhone())) {
			qw.and(new Consumer<QueryWrapper<Customer>>() {
				@Override
				public void accept(QueryWrapper<Customer> customerQueryWrapper) {
					customerQueryWrapper
							.like(StringUtils.isNotBlank(customerVo.getPhone()), "phone", customerVo.getPhone()).or()
							.like(StringUtils.isNotBlank(customerVo.getPhone()), "telephone", customerVo.getPhone());
				}
			});
		}
		customerMapper.selectPage(page, qw);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 保存 客户
	 *
	 * @param customer
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.CustomerServiceImpl", key = "#result.id")
	@Override
	public Customer saveCustomer(Customer customer) {
		this.customerMapper.insert(customer);
		return customer;
	}

	/**
	 * 更新客户
	 *
	 * @param customer
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.CustomerServiceImpl", key = "#result.id")
	@Override
	public Customer updateCustomer(Customer customer) {
		// 为了 解决缓存问题
		// 思路： 当前端某个功能 提交数据 但是数据不全 导致存储到缓存 获取到的数据 缺失
		Customer selectById = this.customerMapper.selectById(customer.getId());
		// 把不为空的属性 覆盖到 selectById 合并成一个 完整的数据集
		BeanUtil.copyProperties(customer, selectById,
				CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		this.customerMapper.updateById(selectById);
		return selectById;
	}

	/**
	 * 查询所有可用 的客户
	 *
	 * @return
	 */
	@Override
	public DataGridView getAllAvailableCustomer() {
		QueryWrapper<Customer> qw = new QueryWrapper<>();
		qw.eq("available", Constant.AVAILABLE_TRUE);
		List<Customer> customers = this.customerMapper.selectList(qw);
		return new DataGridView(customers);
	}

	/**
	 * 根据id 获取商品
	 *
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "com.jun.plugin.business.service.impl.CustomerServiceImpl", key = "#id")
	@Override
	public Customer getById(Serializable id) {
		return super.getById(id);
	}

	/**
	 * 删除客户
	 *
	 * @param id
	 * @return
	 */
	@CacheEvict(cacheNames = "com.jun.plugin.business.service.impl.CustomerServiceImpl", key = "#id")
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}
}

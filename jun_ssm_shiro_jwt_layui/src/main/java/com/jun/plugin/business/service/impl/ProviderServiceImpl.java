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

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.mapper.ProviderMapper;
import com.jun.plugin.business.service.ProviderService;
import com.jun.plugin.business.vo.ProviderVo;
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
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {

	@Autowired
	private ProviderMapper providerMapper;

	/**
	 * 获取所有供应商信息
	 *
	 * @param providerVo
	 * @return
	 */
	@Override
	public DataGridView queryAllProvider(ProviderVo providerVo) {
		IPage<Provider> page = new Page<>(providerVo.getPage(), providerVo.getLimit());
		QueryWrapper<Provider> qw = new QueryWrapper<>();
		qw.like(StringUtils.isNotBlank(providerVo.getCustomername()), "providername", providerVo.getCustomername());
		qw.like(StringUtils.isNotBlank(providerVo.getConnectionperson()), "connectionperson",
				providerVo.getConnectionperson());
		// 进行判断一下 要不然为 null
		if (StringUtils.isNotBlank(providerVo.getPhone())) {
			qw.and(new Consumer<QueryWrapper<Provider>>() {
				@Override
				public void accept(QueryWrapper<Provider> providerQueryWrapper) {
					providerQueryWrapper
							.like(StringUtils.isNotBlank(providerVo.getPhone()), "phone", providerVo.getPhone()).or()
							.like(StringUtils.isNotBlank(providerVo.getPhone()), "telephone", providerVo.getPhone());
				}
			});
		}
		providerMapper.selectPage(page, qw);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 添加供应商
	 *
	 * @param provider
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.ProviderServiceImpl", key = "#result.id")
	@Override
	public Provider saveProvider(Provider provider) {
		providerMapper.insert(provider);
		return provider;
	}

	/**
	 * 更新供应商信息
	 *
	 * @param provider
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.ProviderServiceImpl", key = "#result.id")
	@Override
	public Provider updateProvider(Provider provider) {
		// 为了 解决缓存问题
		// 思路： 当前端某个功能 提交数据 但是数据不全 导致存储到缓存 获取到的数据 缺失
		Provider selectById = this.providerMapper.selectById(provider.getId());
		// 把不为空的属性 覆盖到 selectById 合并成一个 完整的数据集
		BeanUtil.copyProperties(provider, selectById,
				CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

		/*
		 * 方案2 先执行更新 可在返回时进行查询一边
		 */
		this.providerMapper.updateById(selectById);
//            return this.providerMapper.selectById(provider.getId());
		return selectById;

	}

	/**
	 * 查询所有供应商 不分页
	 *
	 * @return
	 */
	@Override
	public DataGridView getAllAvailableProvider() {
		QueryWrapper<Provider> qw = new QueryWrapper<>();
		qw.eq("available", Constant.AVAILABLE_TRUE);
		List<Provider> providers = providerMapper.selectList(qw);
		return new DataGridView(providers);
	}

	/**
	 * 删除指定供应商
	 *
	 * @param id
	 * @return
	 */
	@CacheEvict(cacheNames = "com.jun.plugin.business.service.impl.ProviderServiceImpl", key = "#id")
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	/**
	 * 根据供应商id 获取供应商信息
	 *
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "com.jun.plugin.business.service.impl.ProviderServiceImpl", key = "#id")
	@Override
	public Provider getById(Serializable id) {
		return super.getById(id);
	}

}

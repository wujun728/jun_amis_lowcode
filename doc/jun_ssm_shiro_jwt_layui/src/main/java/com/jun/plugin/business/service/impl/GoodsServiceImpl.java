package com.jun.plugin.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.mapper.GoodsMapper;
import com.jun.plugin.business.mapper.ProviderMapper;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.service.ProviderService;
import com.jun.plugin.business.vo.GoodsVo;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/26
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ProviderService providerService;

	/**
	 * 查询所有商品信息
	 *
	 * @param goodsVo
	 * @return
	 */
	@Override
	public DataGridView queryAllGoods(GoodsVo goodsVo) {
		IPage<Goods> page = new Page<>(goodsVo.getPage(), goodsVo.getLimit());
		QueryWrapper<Goods> qw = new QueryWrapper<>();
		qw.eq(goodsVo.getAvailable() != null, "available", goodsVo.getAvailable());
		qw.eq(goodsVo.getProviderid() != null, "providerid", goodsVo.getProviderid());
		qw.like(StringUtils.isNotBlank(goodsVo.getGoodsname()), "goodsname", goodsVo.getGoodsname());
		qw.like(StringUtils.isNotBlank(goodsVo.getSize()), "size", goodsVo.getSize());
		qw.like(StringUtils.isNotBlank(goodsVo.getProductcode()), "productcode", goodsVo.getProductcode());
		qw.like(StringUtils.isNotBlank(goodsVo.getPromitcode()), "promitcode", goodsVo.getPromitcode());
		qw.like(StringUtils.isNotBlank(goodsVo.getDescription()), "description", goodsVo.getDescription());
		// 执行查询
		this.goodsMapper.selectPage(page, qw);
		// 查询查询到数据
		List<Goods> records = page.getRecords();
		// 循环集合
		for (Goods goods : records) {
			// 进行根据供应商id 查询信息
			if (null != goods.getProviderid()) {
				Provider provider = providerService.getById(goods.getProviderid());
				// 设置供应商名称
				goods.setProvidername(provider.getProvidername());
			}
		}
		return new DataGridView(page.getTotal(), records);
	}

	/**
	 * 查询所有商品数据 不分页
	 *
	 * @return
	 */
	@Override
	public DataGridView getAllAvailableGoods() {
		QueryWrapper<Goods> qw = new QueryWrapper<>();
		qw.eq("available", Constant.AVAILABLE_TRUE);
		// 执行查询
		List<Goods> goods = this.goodsMapper.selectList(qw);
		return new DataGridView(goods);
	}

	/**
	 * 根据供货商id查询 商品
	 *
	 * @param providerid
	 * @return
	 */
	@Override
	public DataGridView getGoodsByProviderId(Integer providerid) {
		if (providerid != null) {
			QueryWrapper<Goods> qw = new QueryWrapper<>();
			qw.eq("available", Constant.AVAILABLE_TRUE);
			qw.eq("providerid", providerid);
			List<Goods> goods = this.goodsMapper.selectList(qw);
			return new DataGridView(goods);
		}
		return new DataGridView();
	}

	/**
	 * 根据商品ID 获取商品信息
	 *
	 * @param goodsid
	 * @return
	 */
	@Override
	public DataGridView getGoodsByGoodId(Integer goodsid) {
		return new DataGridView(this.goodsMapper.selectById(goodsid));
	}

	/**
	 * 保存商品
	 *
	 * @param goods
	 * @return 缓存用
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.GoodsServiceImpl", key = "#result.id")
	@Override
	public Goods saveGoods(Goods goods) {
		this.goodsMapper.insert(goods);
		return goods;
	}

	/**
	 * 更新商品
	 *
	 * @param goods
	 * @return 缓存用
	 */
	@CachePut(cacheNames = "com.jun.plugin.business.service.impl.GoodsServiceImpl", key = "#result.id")
	@Override
	public Goods updateGoods(Goods goods) {
//            Goods selectById = this.goodsMapper.selectById(goods.getId());
//            BeanUtil.copyProperties(goods, selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
//            this.goodsMapper.updateById(goods);
//            return goods;

		// 为了 解决缓存问题
		// 思路： 当前端某个功能 提交数据 但是数据不全 导致存储到缓存 获取到的数据 缺失
		Goods selectById = this.goodsMapper.selectById(goods.getId());
		// 把不为空的属性 覆盖到 selectById 合并成一个 完整的数据集
		BeanUtil.copyProperties(goods, selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		this.goodsMapper.updateById(selectById);
		return selectById;

	}

	@CacheEvict(cacheNames = "com.jun.plugin.business.service.impl.GoodsServiceImpl", key = "#id")
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Cacheable(cacheNames = "com.jun.plugin.business.service.impl.GoodsServiceImpl", key = "#id")
	@Override
	public Goods getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}
}

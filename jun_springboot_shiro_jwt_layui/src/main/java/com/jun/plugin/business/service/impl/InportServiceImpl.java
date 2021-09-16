package com.jun.plugin.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Inport;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.mapper.InportMapper;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.service.InportService;
import com.jun.plugin.business.service.ProviderService;
import com.jun.plugin.business.vo.InportVo;
import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.DataGridView;

import java.util.Date;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/26
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

	@Autowired
	private InportMapper inportMapper;

	@Autowired
	private ProviderService providerService;

	@Autowired
	private GoodsService goodsService;

	/**
	 * 查询进货信息
	 *
	 * @param inportVo
	 * @return
	 */
	@Override
	public DataGridView queryAllInport(InportVo inportVo) {

		IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());

		QueryWrapper<Inport> qw = new QueryWrapper<>();

		qw.eq(inportVo.getGoodsid() != null, "goodsid", inportVo.getGoodsid());
		qw.eq(inportVo.getProviderid() != null, "providerid", inportVo.getProviderid());

		qw.ge(inportVo.getStartTime() != null, "inporttime", inportVo.getStartTime());
		qw.le(inportVo.getEndTime() != null, "inporttime", inportVo.getEndTime());

		qw.orderByDesc("inporttime");

		this.inportMapper.selectPage(page, qw);

		List<Inport> records = page.getRecords();
		// 循环替换供货商和商品 名称
		for (Inport record : records) {
			if (null != record.getProviderid()) {
				Provider byId = providerService.getById(record.getProviderid());
				record.setProvidername(byId.getProvidername());
			}

			if (null != record.getGoodsid()) {
				Goods byId = goodsService.getById(record.getGoodsid());
				record.setGoodsname(byId.getGoodsname());
				record.setSize(byId.getSize());
			}
		}
		return new DataGridView(page.getTotal(), records);
	}

	/**
	 * 保存进货信息 思路： 当商品进货 对应的商品库存增加
	 *
	 * @param inport
	 * @return
	 */
	@Override
	public Inport saveInport(Inport inport) {
		// 进货
		this.inportMapper.insert(inport);
		// 商品库存
		Goods byId = this.goodsService.getById(inport.getGoodsid());
		// 更新库存
		byId.setNumber(byId.getNumber() + inport.getNumber());
		this.goodsService.updateGoods(byId);
		System.out.println("保存库存:" + byId.getNumber());
		return inport;
	}

	/**
	 * 更新进货信息
	 *
	 * @param inport
	 * @return
	 */
	@Override
	public Inport updateInport(Inport inport) {
		// 获取进货数量
		Inport oldObj = this.inportMapper.selectById(inport.getId());
		// 获取最新进货数量
		Goods newObj = this.goodsService.getById(oldObj.getGoodsid());
		// 用newObje 减去 oldoBJE 加上现在传入来的 值 // 1000 100 1100 50
		newObj.setNumber(newObj.getNumber() - oldObj.getNumber() + inport.getNumber());
		this.goodsService.updateById(newObj);
		this.inportMapper.updateById(inport);
		System.out.println("更新goods库存:" + newObj.getNumber());
		System.out.println("更新inport库存:" + newObj.getNumber());
		return inport;
	}

	/**
	 * 删除进货信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {
		// 获取进货数量 50
		Inport oldObj = this.inportMapper.selectById(id);
		// 获取最新进货数量 150
		Goods newObj = this.goodsService.getById(oldObj.getGoodsid());
		// 删除进货信息 也要删除 进货数量
		newObj.setNumber(newObj.getNumber() - oldObj.getNumber());
		this.goodsService.updateById(newObj);
		System.out.println("删除库存:" + newObj.getNumber());
		// 最终删除 进货信息
		return super.removeById(id);
	}

}

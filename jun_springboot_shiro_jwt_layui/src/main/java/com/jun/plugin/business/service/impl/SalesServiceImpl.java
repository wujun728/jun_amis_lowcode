package com.jun.plugin.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Inport;
import com.jun.plugin.business.domain.Sales;
import com.jun.plugin.business.mapper.SalesMapper;
import com.jun.plugin.business.service.CustomerService;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.service.SalesService;
import com.jun.plugin.business.vo.SalesVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {

	@Autowired
	private SalesMapper salesMapper;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerService customerService;

	/**
	 * 查询全部商品销售信息
	 *
	 * @param salesVo
	 * @return
	 */
	@Override
	public DataGridView queryAllSales(SalesVo salesVo) {

		IPage<Sales> page = new Page<>(salesVo.getPage(), salesVo.getLimit());

		QueryWrapper<Sales> qw = new QueryWrapper<>();

		qw.eq(salesVo.getGoodsid() != null, "goodsid", salesVo.getGoodsid());
		qw.eq(salesVo.getCustomerid() != null, "customerid", salesVo.getCustomerid());

		qw.ge(salesVo.getStartTime() != null, "salestime", salesVo.getStartTime());
		qw.le(salesVo.getEndTime() != null, "salestime", salesVo.getEndTime());

		qw.orderByDesc("salestime");

		this.salesMapper.selectPage(page, qw);
		List<Sales> records = page.getRecords();
		for (Sales record : records) {
			if (null != record.getGoodsid()) {
				Goods goods = this.goodsService.getById(record.getGoodsid());
				if (null != goods) {
					record.setGoodsname(goods.getGoodsname());
					record.setSize(goods.getSize());
				}
			}
			if (null != record.getCustomerid()) {
				Customer customer = this.customerService.getById(record.getCustomerid());
				if (customer != null) {
					record.setCustomername(customer.getCustomername());
				}
			}
		}
		return new DataGridView(page.getTotal(), records);
	}

	@Override
	public Sales saveSales(Sales sales) {
		this.salesMapper.insert(sales);
		// 进行销售商品
		Integer goodsid = sales.getGoodsid();
		Goods goods = this.goodsService.getById(goodsid);
		// 销售 减去商品数量
		goods.setNumber(goods.getNumber() - sales.getNumber());
		this.goodsService.updateGoods(goods);
		return sales;
	}

	@Override
	public Sales updateSales(Sales sales) {
		Sales oldObj = this.salesMapper.selectById(sales.getId());
		Goods goods = this.goodsService.getById(oldObj.getGoodsid());
		goods.setNumber(goods.getNumber() + oldObj.getNumber() - sales.getNumber());
		this.goodsService.updateGoods(goods);
		this.salesMapper.updateById(sales);
		return sales;
	}

	/**
	 * 删除商品销售信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {
		Sales sales = this.salesMapper.selectById(id);
		Goods goods = this.goodsService.getById(sales.getGoodsid());
		goods.setNumber(goods.getNumber() + sales.getNumber());
		this.goodsService.updateGoods(goods);
		return super.removeById(id);
	}

}

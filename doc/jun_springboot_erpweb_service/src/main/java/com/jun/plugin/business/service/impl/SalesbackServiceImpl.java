package com.jun.plugin.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Customer;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Sales;
import com.jun.plugin.business.domain.Salesback;
import com.jun.plugin.business.mapper.SalesMapper;
import com.jun.plugin.business.mapper.SalesbackMapper;
import com.jun.plugin.business.service.CustomerService;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.service.SalesService;
import com.jun.plugin.business.service.SalesbackService;
import com.jun.plugin.business.vo.SalesbackVo;
import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class SalesbackServiceImpl extends ServiceImpl<SalesbackMapper, Salesback> implements SalesbackService {

	@Autowired
	private SalesbackMapper salesbackMapper;
	@Autowired
	private SalesMapper salesMapper;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerService customerService;

	@Override
	public DataGridView queryAllSalesback(SalesbackVo salesbackVo) {

		IPage<Salesback> page = new Page<>(salesbackVo.getPage(), salesbackVo.getLimit());

		QueryWrapper<Salesback> qw = new QueryWrapper<>();

		qw.eq(salesbackVo.getGoodsid() != null, "goodsid", salesbackVo.getGoodsid());
		qw.eq(salesbackVo.getCustomerid() != null, "customerid", salesbackVo.getCustomerid());

		qw.ge(salesbackVo.getStartTime() != null, "salesbacktime", salesbackVo.getStartTime());
		qw.le(salesbackVo.getEndTime() != null, "salesbacktime", salesbackVo.getEndTime());

		qw.orderByDesc("salesbacktime");

		this.salesbackMapper.selectPage(page, qw);
		List<Salesback> records = page.getRecords();
		for (Salesback record : records) {

			System.out.println(record);

			if (null != record.getGoodsid()) {
				Goods goods = this.goodsService.getById(record.getGoodsid());
				record.setGoodsname(goods.getGoodsname());
				record.setSize(goods.getSize());
			}

			if (null != record.getCustomerid()) {
				Customer customer = this.customerService.getById(record.getCustomerid());
				record.setCustomername(customer.getCustomername());
			}
		}

		return new DataGridView(page.getTotal(), records);
	}

	@Override
	public Salesback saveSalesback(Salesback salesback) {
		// 页面传入 备注 与 退货数量
		// 获取销售id
		Integer salesid = salesback.getSalesid();
		// 获取根据id 获取销售 信息
		Sales sales = this.salesMapper.selectById(salesid);
		// 设置退货信息
		salesback.setGoodsid(sales.getGoodsid());
		salesback.setPaytype(sales.getPaytype());
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
		salesback.setSalebackprice(sales.getSaleprice());
		salesback.setCustomerid(sales.getCustomerid());
		salesback.setOperateperson(activeUser.getUser().getName());
		salesback.setSalesbacktime(new Date());

		// 保存退货信息
		this.salesbackMapper.insert(salesback);

		// 增加库存 --- 销售商品 退货 到 商品仓库
		Goods goods = this.goodsService.getById(sales.getGoodsid());
		goods.setNumber(goods.getNumber() + salesback.getNumber());
		// 更新商品库存
		this.goodsService.updateGoods(goods);

		// 更新销售库存
		sales.setNumber(sales.getNumber() - salesback.getNumber());
		salesMapper.updateById(sales);
		return salesback;
	}
}

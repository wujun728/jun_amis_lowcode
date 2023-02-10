package com.jun.plugin.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Inport;
import com.jun.plugin.business.domain.Outport;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.mapper.GoodsMapper;
import com.jun.plugin.business.mapper.InportMapper;
import com.jun.plugin.business.mapper.OutportMapper;
import com.jun.plugin.business.service.GoodsService;
import com.jun.plugin.business.service.OutportService;
import com.jun.plugin.business.service.ProviderService;
import com.jun.plugin.business.vo.OutportVo;
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
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {
	@Autowired
	private OutportMapper outportMapper;
	@Autowired
	private InportMapper inportMapper;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProviderService providerService;

	@Override
	public Outport saveOutport(Outport outport) {
		// 获取进货id
		Integer inportid = outport.getInportid();
		// 获取进货对象
		Inport inport = inportMapper.selectById(inportid);
		// 设置商品id
		outport.setGoodsid(inport.getGoodsid());
		// 设置库存数量
		outport.setNumber(outport.getNumber());
		// 设置支付状态
		outport.setPaytype(inport.getPaytype());
		// 设置操作人
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
		outport.setOperateperson(activeUser.getUser().getName());
		// 设置退货时间
		outport.setOutporttime(new Date());
		// 设置退货价格
		outport.setOutportprice(inport.getInportprice());
		// 设置供应商id
		outport.setProviderid(inport.getProviderid());

		// 保存退货信息
		this.outportMapper.insert(outport);

		// 减少 商品 库存
		Goods goods = this.goodsService.getById(inport.getGoodsid());
		goods.setNumber(goods.getNumber() - outport.getNumber());
		this.goodsService.updateGoods(goods);
		return outport;
	}

	@Override
	public DataGridView queryAllOutport(OutportVo outportVo) {

		IPage<Outport> page = new Page<>(outportVo.getPage(), outportVo.getLimit());

		QueryWrapper<Outport> qw = new QueryWrapper<>();

		qw.eq(outportVo.getGoodsid() != null, "goodsid", outportVo.getGoodsid());
		qw.eq(outportVo.getProviderid() != null, "providerid", outportVo.getProviderid());

		qw.ge(outportVo.getStartTime() != null, "outporttime", outportVo.getStartTime());
		qw.le(outportVo.getEndTime() != null, "outporttime", outportVo.getEndTime());

		qw.orderByDesc("outporttime");

		this.outportMapper.selectPage(page, qw);

		List<Outport> records = page.getRecords();
		// 循环替换供货商和商品 名称
		for (Outport record : records) {
			if (null != record.getProviderid()) {
				Provider byId = this.providerService.getById(record.getProviderid());
				record.setProvidername(byId.getProvidername());
			}

			if (null != record.getGoodsid()) {
				Goods goods = this.goodsService.getById(record.getGoodsid());
				if (goods != null) {
					record.setGoodsname(goods.getGoodsname());
					record.setSize(goods.getSize());
				}
			}

		}

		return new DataGridView(page.getTotal(), records);
	}
}

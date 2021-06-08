package com.qingshop.mall.modules.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.mall.entity.MallSaleArea;
import com.qingshop.mall.modules.mall.entity.MallShipFree;
import com.qingshop.mall.modules.mall.entity.MallShipRule;
import com.qingshop.mall.modules.mall.entity.MallShipSet;
import com.qingshop.mall.modules.mall.mapper.MallShipSetMapper;
import com.qingshop.mall.modules.mall.service.IMallSaleAreaService;
import com.qingshop.mall.modules.mall.service.IMallShipFreeService;
import com.qingshop.mall.modules.mall.service.IMallShipRuleService;
import com.qingshop.mall.modules.mall.service.IMallShipSetService;
import com.qingshop.mall.modules.mall.vo.ShipSetVo;

/**
 * 配送设置表 服务实现类
 */
@Service
public class MallShipSetServiceImpl extends ServiceImpl<MallShipSetMapper, MallShipSet> implements IMallShipSetService {
	
	@Autowired
	private IMallSaleAreaService mallSaleAreaService;
	
	@Autowired
	private IMallShipRuleService mallShipRuleService;

	@Autowired
	private IMallShipFreeService mallShipFreeService;

	@Override
	public Rest insertShipSet(ShipSetVo shipSet) {
		MallShipSet mallShipSet = new MallShipSet();
		mallShipSet.setSaleAreaType(shipSet.getSaleAreaType());
		mallShipSet.setIsSelfMention(shipSet.getIsSelfMention());
		mallShipSet.setFreightType(shipSet.getFreightType());
		mallShipSet.setDefaultWeight(shipSet.getDefaultWeight());
		mallShipSet.setDefaultExpense(shipSet.getDefaultExpense());
		mallShipSet.setContinueExpense(shipSet.getContinueExpense());
		List<MallSaleArea> saleList = shipSet.getSaleArea();
		for (MallSaleArea mallSaleArea : saleList) {
			mallSaleArea.setSaleAreaId(DistributedIdWorker.nextId());
		}
		List<MallShipRule> shipLists = shipSet.getShipRule();
		for (MallShipRule mallShipRule : shipLists) {
			mallShipRule.setRuleId(DistributedIdWorker.nextId());
		}
		List<MallShipFree> shipFreeList = shipSet.getFreeShip();
		for (MallShipFree mallShipFree : shipFreeList) {
			mallShipFree.setFreeId(DistributedIdWorker.nextId());
		}
		boolean saveShipSet = true;
		if(shipSet.getSetId() == null) {
			mallShipSet.setSetId(DistributedIdWorker.nextId());
			saveShipSet = this.save(mallShipSet);
		}else {
			mallShipSet.setSetId(shipSet.getSetId());
			mallShipSet.setCreateTime(new Date());
			mallShipSet.setUpdateTime(new Date());
			saveShipSet = this.updateById(mallShipSet);
		}
		mallSaleAreaService.remove(null);
		boolean saveSaleArea = true;
		if(StringUtils.isNotEmpty(saleList)) {
			saveSaleArea = mallSaleAreaService.saveBatch(saleList);
		}
		mallShipRuleService.remove(null);
		boolean saveShipRule = true; 
		if(StringUtils.isNotEmpty(shipLists)) {
			saveShipRule = mallShipRuleService.saveBatch(shipLists);
		}
		mallShipFreeService.remove(null);
		boolean saveShipFree = true;
		if(StringUtils.isNotEmpty(shipFreeList)) {
			saveShipFree = mallShipFreeService.saveBatch(shipFreeList);
		}
		if(saveShipSet && saveSaleArea && saveShipRule && saveShipFree) {
			return Rest.ok();
		}else {
			return Rest.failure();
		}
	}

}

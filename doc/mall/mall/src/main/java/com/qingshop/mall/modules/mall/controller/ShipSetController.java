package com.qingshop.mall.modules.mall.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.mall.entity.MallSaleArea;
import com.qingshop.mall.modules.mall.entity.MallShipFree;
import com.qingshop.mall.modules.mall.entity.MallShipRule;
import com.qingshop.mall.modules.mall.entity.MallShipSet;
import com.qingshop.mall.modules.mall.service.IMallSaleAreaService;
import com.qingshop.mall.modules.mall.service.IMallShipFreeService;
import com.qingshop.mall.modules.mall.service.IMallShipRuleService;
import com.qingshop.mall.modules.mall.service.IMallShipSetService;
import com.qingshop.mall.modules.mall.vo.ShipSetVo;

@Controller
@RequestMapping("/mall/shipSet")
public class ShipSetController {

	@Autowired
	private IMallShipSetService mallShipSetService;

	@Autowired
	private IMallSaleAreaService mallSaleAreaService;

	@Autowired
	private IMallShipRuleService mallShipRuleService;

	@Autowired
	private IMallShipFreeService mallShipFreeService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listShipSet")
	@RequestMapping("/list")
	public String list(Model model) {
		MallShipSet mallShipSet = new MallShipSet();
		List<MallShipSet> shipSetList = mallShipSetService.list(null);
		if (StringUtils.isNotEmpty(shipSetList)) {
			mallShipSet = shipSetList.get(0);
		}
		List<MallSaleArea> saleAreaList = mallSaleAreaService.list(null);
		List<MallShipRule> shipRuleList = mallShipRuleService.list(null);
		List<MallShipFree> shipfreeList = mallShipFreeService.list(null);
		model.addAttribute("mallShipSet", mallShipSet);
		model.addAttribute("saleAreaList", saleAreaList);
		model.addAttribute("shipRuleList", shipRuleList);
		model.addAttribute("shipfreeList", shipfreeList);
		return "mall/shipset/shipset";
	}

	@RequiresPermissions("editShipSet")
	@PostMapping("/saveShipSet")
	@ResponseBody
	public Rest saveShipSet(@RequestBody ShipSetVo shipSet) {
		return mallShipSetService.insertShipSet(shipSet);
	}

}

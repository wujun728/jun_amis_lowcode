package com.qingshop.mall.modules.mall.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallDeliveryCompany;
import com.qingshop.mall.modules.mall.service.IMallDeliveryCompanyService;
import com.qingshop.mall.modules.mall.service.IMallRegionService;

/**
 * 省市区信息表 前端控制器
 */
@Controller
@RequestMapping("/mall/region")
public class RegionController extends BaseController {
	
	@Autowired
	private IMallRegionService mallRegionService;
	
	@Autowired
	private IMallDeliveryCompanyService mallDeliveryCompanyService;
	
	/**
	 * 列表页
	 */
	@RequestMapping("/listCommonArea")
	@ResponseBody
	public Rest listCommonArea() {
		return Rest.okData(mallRegionService.listCommonArea());
	}
	
	/**
	 * 根据父节点获取子菜单
	 */
	@RequestMapping("/deliverCompany")
	@ResponseBody
	public Rest json(Long pid) {
		QueryWrapper<MallDeliveryCompany> ew = new QueryWrapper<MallDeliveryCompany>();
		List<MallDeliveryCompany> list = mallDeliveryCompanyService.list(ew);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (MallDeliveryCompany m : list) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", m.getDvyCode());
			map.put("text", m.getDvyName());
			listMap.add(map);
		}
		return Rest.okData(listMap);
	}
	
}

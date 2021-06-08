package com.qingshop.mall.modules.mall.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;

@Controller
@RequestMapping("/mall/stock")
public class StockController extends BaseController {

	@Autowired
	private IMallGoodsService mallGoodsServic;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listStock")
	@RequestMapping("/list")
	public String list() {
		return "mall/stock/list";
	}

	/**
	 * 分页查询日志
	 */
	@RequiresPermissions("listStock")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest list(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<MallGoods> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<MallGoods> ew = new QueryWrapper<MallGoods>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("good_name", search);
		}
		ew.orderByDesc("create_time");
		IPage<Map<String, Object>> pageData = mallGoodsServic.pageMaps(page, ew);
		List<Map<String, Object>> list = pageData.getRecords();
		List<Long> goodIdList = list.stream().map(m -> (Long) m.get("goodsId")).collect(Collectors.toList());
		List<Map<String, Object>> skudetails = mallGoodsSkudetailService.listMaps(new QueryWrapper<MallGoodsSkudetail>().in("goods_id", goodIdList));
		Map<Object, Integer> skudetailSumMaps = skudetails.stream().collect(Collectors.groupingBy(m -> m.get("goodsId"), Collectors.summingInt(m -> Integer.valueOf(m.get("number").toString()))));
		for (Map<String, Object> mallGoods : list) {
			mallGoods.put("stockNum", skudetailSumMaps.get(mallGoods.get("goodsId")));
		}
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", list);
		return resultMap;
	}

	/**
	 * 库存详情
	 */
	@RequiresPermissions("listStock")
	@RequestMapping("/detail/{id}")
	public String edit(@PathVariable Long id, Model model) {
		List<MallGoodsSkudetail> skudetails = mallGoodsSkudetailService.list(new QueryWrapper<MallGoodsSkudetail>().eq("goods_id", id));
		model.addAttribute("skudetails", skudetails);
		return "mall/stock/detail";
	}

	/**
	 * 执行修改
	 */
	@RequiresPermissions("editStock")
	@RequestMapping("/editStock")
	@ResponseBody
	public Rest editStock(MallGoodsSkudetail mallGoodsSkudetail) {
		if (mallGoodsSkudetailService.updateById(mallGoodsSkudetail)) {
			return Rest.ok();
		} else {
			return Rest.failure();
		}
	}

}

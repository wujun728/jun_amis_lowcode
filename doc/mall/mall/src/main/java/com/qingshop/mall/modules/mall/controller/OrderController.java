package com.qingshop.mall.modules.mall.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.KdniaoTrackQueryAPI;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallOrder;
import com.qingshop.mall.modules.mall.entity.MallOrderDetail;
import com.qingshop.mall.modules.mall.service.IMallOrderDetailService;
import com.qingshop.mall.modules.mall.service.IMallOrderService;

@Controller
@RequestMapping("/mall/order")
public class OrderController extends BaseController {

	@Autowired
	private IMallOrderService mallOrderService;

	@Autowired
	private IMallOrderDetailService mallOrderDetailService;

	@RequiresPermissions("listOrder")
	@RequestMapping("/list")
	public String lits() {
		return "mall/order/list";
	}

	@RequiresPermissions("listOrder")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Page<MallOrder> page = getPage(pageIndex, length);
		Rest resultMap = new Rest();
		QueryWrapper<MallOrder> ew = new QueryWrapper<MallOrder>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("name", search);
		}
		ew.orderByDesc("create_time");
		IPage<Map<String, Object>> pageData = mallOrderService.pageMaps(page, ew);
		List<Map<String, Object>> lits = pageData.getRecords();
		List<Long> orderIdList = lits.stream().map(m -> (Long) m.get("orderId")).collect(Collectors.toList());
		List<MallOrderDetail> orderDetailLit = mallOrderDetailService.list(new QueryWrapper<MallOrderDetail>().in("order_id", orderIdList));
		Map<String, MallOrderDetail> detailMap = new HashMap<String, MallOrderDetail>();
		for (MallOrderDetail mallOrderDetail : orderDetailLit) {
			String orderId = mallOrderDetail.getOrderId().toString();
			if (detailMap.containsKey(orderId)) {
				Integer number = detailMap.get(orderId).getNumber();
				Integer totalNumber = number + mallOrderDetail.getNumber();
				detailMap.get(orderId).setNumber(totalNumber);
			} else {
				detailMap.put(orderId, mallOrderDetail);
			}
		}
		for (Map<String, Object> map : lits) {
			String order = map.get("orderId").toString();
			MallOrderDetail mallOrderDetail = detailMap.get(order);
			map.put("picUrl", mallOrderDetail.getPicUrl());
			map.put("number", mallOrderDetail.getNumber());
		}
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", lits);
		return resultMap;
	}

	@RequiresPermissions("listOrder")
	@RequestMapping("/detail/{id}")
	public String orderDetail(@PathVariable Long id, Model model) {
		MallOrder mallOrder = mallOrderService.getById(id);
		List<MallOrderDetail> orderDetailLit = mallOrderDetailService.list(new QueryWrapper<MallOrderDetail>().eq("order_id", id));
		model.addAttribute("mallOrder", mallOrder);
		model.addAttribute("orderDetailLit", orderDetailLit);
		return "mall/order/detail";
	}

	/**
	 * 确认收货
	 */
	@RequiresPermissions("editOrder")
	@PostMapping("deliver")
	@ResponseBody
	public Rest deliver(String orderId, String shipChannel, String shipSn) {
		if ("".equals(orderId)) {
			return Rest.failure("操作失败");
		}
		MallOrder order = new MallOrder();
		order.setOrderId(Long.valueOf(orderId));
		order.setShipChannel(shipChannel);
		order.setShipSn(shipSn);
		order.setShipStatus(1);
		order.setShipTime(new Date());
		if (mallOrderService.updateById(order)) {
			return Rest.ok("操作成功");
		} else {
			return Rest.failure("操作失败");
		}
	}

	/**
	 * 物流信息
	 */
	@PostMapping("expressTrack")
	@ResponseBody
	public Rest expressTrack(String shipChannel, String shipSn) {
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		try {
			String result = api.getOrderTracesByJson(shipChannel, shipSn);
			return Rest.okData(result);
		} catch (Exception e) {
			e.printStackTrace();
			return Rest.failure("操作失败");
		}
	}

}

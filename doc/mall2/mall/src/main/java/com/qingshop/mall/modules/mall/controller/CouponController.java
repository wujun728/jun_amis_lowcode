package com.qingshop.mall.modules.mall.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallCouponUser;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallCouponUserService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallUserService;

@Controller
@RequestMapping("/mall/market")
public class CouponController extends BaseController {

	@Autowired
	private IMallGoodsService mallGoodsService;

	@Autowired
	private IMallCategoryService mallCategoryService;

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallCouponUserService mallCouponUserService;

	@Autowired
	private IMallUserService mallUserService;

	@RequiresPermissions("listMarket")
	@RequestMapping("/list")
	public String list() {
		return "/mall/market/list";
	}

	@RequiresPermissions("listMarket")
	@RequestMapping("/marketslist")
	@ResponseBody
	public Rest marketslist(String status) {
		Map<String, Object> data = new HashedMap<>();
		List<MallCoupon> couponList = new ArrayList<>();
		if ("0".equals(status)) {
			couponList = mallCouponService.list(new QueryWrapper<MallCoupon>().orderByDesc("create_time"));
		}
		if ("1".equals(status)) {
			couponList = mallCouponService
					.list(new QueryWrapper<MallCoupon>().eq("activity_status", 1).orderByDesc("create_time"));
		}
		if ("2".equals(status)) {
			couponList = mallCouponService
					.list(new QueryWrapper<MallCoupon>().eq("activity_status", 2).orderByDesc("create_time"));
		}
		List<Long> categoryIdList = couponList.stream().filter(m -> m.getRangeType() == 1)
				.map(m -> (Long) m.getRangeRelationId()).collect(Collectors.toList());
		List<MallCategory> categoryList = new ArrayList<>();
		if (!StringUtils.isEmpty(categoryIdList)) {
			categoryList = (List<MallCategory>) mallCategoryService.listByIds(categoryIdList);
		}
		Map<Long, MallCategory> goodTypeMapMap = categoryList.stream()
				.collect(Collectors.toMap(MallCategory::getCategoryId, m -> m));
		List<Long> goodIdList = couponList.stream().filter(m -> m.getRangeType() == 2)
				.map(m -> (Long) m.getRangeRelationId()).collect(Collectors.toList());
		List<MallGoods> goodList = new ArrayList<>();
		if (!StringUtils.isEmpty(goodIdList)) {
			goodList = (List<MallGoods>) mallGoodsService.listByIds(goodIdList);
		}
		Map<Long, MallGoods> goodMapMap = goodList.stream().collect(Collectors.toMap(MallGoods::getGoodsId, m -> m));
		Iterator<MallCoupon> couponIterator = couponList.iterator();
		while (couponIterator.hasNext()) {
			MallCoupon coupon = couponIterator.next();
			Integer rangeType = coupon.getRangeType();
			Long rangeRelationId = coupon.getRangeRelationId();
			Integer activityStatus = coupon.getActivityStatus();
			if (rangeType == 1) {
				MallCategory goodTypeMap = goodTypeMapMap.get(rangeRelationId);
				if (null != goodTypeMap) {
					String typeStatus = goodTypeMap.getStatus();
					if (activityStatus == 1 && "0".equals(typeStatus)) {
						coupon.setActivityStatus(2);
					}
				} else {
					couponIterator.remove();
				}
			} else if (rangeType == 2) {
				MallGoods goodMap = goodMapMap.get(rangeRelationId);
				if (null != goodMap) {
					Integer isShlf = goodMap.getIsShelf();
					if (activityStatus == 1 && isShlf == 0) {
						coupon.setActivityStatus(2);
					}
				} else {
					couponIterator.remove();
				}
			}
		}
		data.put("couponList", couponList);
		return Rest.okData(data);
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("addMarket")
	@RequestMapping("/add")
	public String add(Model model) {
		List<MallCategory> mallCategories = mallCategoryService.list(null);
		// 数据分组
		Map<String, List<MallCategory>> categoryMapList = new HashMap<String, List<MallCategory>>();
		for (MallCategory mallCategory : mallCategories) {
			if (mallCategory.getParentId() == 0L) {
				List<MallCategory> tmpList = new ArrayList<MallCategory>();
				tmpList.add(mallCategory);
				categoryMapList.put(mallCategory.getCategoryId().toString(), tmpList);
			} else if (categoryMapList.containsKey(mallCategory.getParentId().toString())) {
				categoryMapList.get(mallCategory.getParentId().toString()).add(mallCategory);
			}
		}
		List<MallGoods> goodList = mallGoodsService.list(null);
		model.addAttribute("categoryMapList", categoryMapList);
		model.addAttribute("goodList", goodList);
		return "mall/market/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addMarket")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(MallCoupon mallCoupon) {
		if (StringUtils.isEmpty(mallCoupon.getCouponName())) {
			return Rest.failure("活动名称不能为空");
		}
		if (mallCoupon.getConditionNum() == null || mallCoupon.getConditionNum() <= 0) {
			return Rest.failure("活动门槛错误");
		}
		if (mallCoupon.getDiscount() == null || mallCoupon.getDiscount().compareTo(new BigDecimal(0.0)) != 1) {
			return Rest.failure("满减金额错误");
		}
		if (mallCoupon.getTotalNum() == null || mallCoupon.getTotalNum() < 0) {
			return Rest.failure("优惠券数量错误");
		}
		if (mallCoupon.getTimeType() == 0) {
			if (StringUtils.isEmpty(mallCoupon.getStartTime().toString())
					|| StringUtils.isEmpty(mallCoupon.getEndTime().toString())) {
				return Rest.failure("有效期限开始时间或结束时间不能为空");
			} else {
				if (mallCoupon.getStartTime().after(mallCoupon.getEndTime())) {
					return Rest.failure("有效期限结束时间不能早于开始时间");
				}
			}
		}
		Integer rangeType = mallCoupon.getRangeType();
		Long rangeRelationId = mallCoupon.getRangeRelationId();
		String rangeRelationName = mallCoupon.getRangeRelationName();
		if (rangeType == 1 && (rangeRelationId == null || StringUtils.isEmpty(rangeRelationName))) {
			return Rest.failure("指定分类选择不能为空");
		}
		if (rangeType == 2 && (rangeRelationId == null || StringUtils.isEmpty(rangeRelationName))) {
			return Rest.failure("指定商品选择不能为空");
		}
		mallCoupon.setCouponId(DistributedIdWorker.nextId());
		mallCoupon.setActivityStatus(1);
		mallCoupon.setRemainNum(mallCoupon.getTotalNum());
		mallCouponService.save(mallCoupon);
		return Rest.ok();
	}

	/**
	 * 详情
	 */
	@RequestMapping("/detail/{id}")
	public String edit(@PathVariable Long id, Model model) {
		MallCoupon coupon = mallCouponService.getById(id);
		Integer rangeType = coupon.getRangeType();
		Long rangeRelationId = coupon.getRangeRelationId();
		Integer activityStatus = coupon.getActivityStatus();
		if (rangeType == 1) {
			MallCategory goodTypeMap = mallCategoryService.getById(rangeRelationId);
			if (null != goodTypeMap) {
				String typeStatus = goodTypeMap.getStatus();
				if (activityStatus == 1 && "0".equals(typeStatus)) {
					coupon.setActivityStatus(2);
				}
			}
		} else if (rangeType == 2) {
			MallGoods goodMap = mallGoodsService.getById(rangeRelationId);
			if (null != goodMap) {
				Integer isShlf = goodMap.getIsShelf();
				if (activityStatus == 1 && isShlf == 0) {
					coupon.setActivityStatus(2);
				}
			}
		}
		model.addAttribute("coupon", coupon);
		return "mall/market/detail";
	}

	/**
	 * 取消
	 */
	@RequiresPermissions("editMarket")
	@PostMapping("/cancel")
	@ResponseBody
	public Rest cancel(Long couponId) {
		if (couponId == null) {
			return Rest.failure("操作失败");
		}
		MallCoupon coupon = new MallCoupon();
		coupon.setCouponId(couponId);
		coupon.setActivityStatus(2);
		coupon.setDelTime(new Date());
		if (mallCouponService.updateById(coupon)) {
			return Rest.ok();
		} else {
			return Rest.failure("操作失败");
		}
	}

	@RequiresPermissions("listMarket")
	@RequestMapping("/record/{couponId}")
	public String record(@PathVariable Long couponId, Model model) {
		model.addAttribute("couponId", couponId);
		return "mall/market/record";
	}

	@RequiresPermissions("listMarket")
	@RequestMapping("/listRecord")
	@ResponseBody
	public Rest listRecord(String search, Long couponId) {
		if (couponId == null) {
			Rest.failure("暂无数据");
		}
		Rest resultMap = new Rest();
		QueryWrapper<MallCouponUser> ew = new QueryWrapper<MallCouponUser>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("name", search);
		}
		ew.eq("coupon_id", couponId);
		List<Map<String, Object>> couponUserList = mallCouponUserService.listMaps(ew);
		if (!StringUtils.isEmpty(couponUserList)) {
			List<Long> useIdList = couponUserList.stream().map(m -> Long.valueOf(m.get("userId").toString())).collect(Collectors.toList());
			List<MallUser> userList = mallUserService.list(new QueryWrapper<MallUser>().in("user_id", useIdList));
			Map<Long, MallUser> userMap = userList.stream().collect(Collectors.toMap(MallUser::getUserId, m -> m));
			for (Map<String, Object> map : couponUserList) {
				map.put("user_name", userMap.get(map.get("userId")).getUsername());
				map.put("userMobile", userMap.get(map.get("userId")).getUserMobile());
			}
		}
		resultMap.put("iTotalDisplayRecords", couponUserList.size());
		resultMap.put("iTotalRecords", couponUserList.size());
		resultMap.put("aaData", couponUserList);
		return resultMap;
	}
}

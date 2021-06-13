package com.qingshop.mall.modules.wxapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.DateUtils;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallCouponUser;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallCouponUserService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;

@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController extends BaseController {

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallCouponUserService mallCouponUserService;

	@Autowired
	private IMallCategoryService mallCategoryService;

	@Autowired
	private IMallGoodsService mallGoodsService;

	@GetMapping("/list")
	public Rest couponList(Integer page, Integer limit) {
		Map<String, Object> data = new HashMap<String, Object>();
		Page<MallCoupon> pages = this.getPage(page, limit);
		QueryWrapper<MallCoupon> ew = new QueryWrapper<MallCoupon>().eq("activity_status", 1);
		ew.orderByDesc("create_time");
		IPage<MallCoupon> pageData = mallCouponService.page(pages, ew);
		List<MallCoupon> couponList = pageData.getRecords();
		Iterator<MallCoupon> couponIterator = couponList.iterator();
		while (couponIterator.hasNext()) {
			MallCoupon coupon = couponIterator.next();
			// 绝对有效期剔除过期劵
			Integer timeType = coupon.getTimeType();
			if (timeType == 0 && coupon.getEndTime().before(new Date())) {
				couponIterator.remove();
				continue;
			}
			Integer rangeType = coupon.getRangeType();
			if (rangeType == 1) {
				coupon.setRangeRelationName("仅限（" + coupon.getRangeRelationName() + "）类别使用");
			} else if (rangeType == 2) {
				coupon.setRangeRelationName("仅限（" + coupon.getRangeRelationName() + "）使用");
			}
		}
		data.put("list", couponList);
		data.put("total", couponList.size());
		return Rest.okData(data);
	}

	/**
	 * 优惠券领取
	 *
	 * @param userId
	 *            用户ID
	 * @param body
	 *            请求内容， { couponId: xxx }
	 * @return 操作结果
	 */
	@PostMapping("/receive")
	public Rest receive(@LoginUser Long userId, @RequestBody String body) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		JSONObject json = JSONObject.parseObject(body);
		Long couponId = json.getLong("couponId");
		if (couponId == null) {
			return Rest.failure("优惠券不存在");
		}
		MallCoupon coupon = mallCouponService.getById(couponId);
		if (coupon == null) {
			return Rest.failure();
		}

		// 当前用户已领取数量和用户限领数量比较
		Integer userCounpons = mallCouponUserService.count(new QueryWrapper<MallCouponUser>().eq("coupon_id", couponId).eq("user_id", userId));
		if (userCounpons >= 1) {
			return Rest.failure("优惠券已经领取过");
		}
		// 优惠券状态，已下架或者过期不能领取
		Integer activityStatus = coupon.getActivityStatus();
		if (activityStatus == 0 || activityStatus == 2) {
			return Rest.failure("优惠券已经下架");
		}
		// 商品关联，商品分类关联状态检测
		Integer rangType = coupon.getRangeType();
		if (rangType == 1) {
			Long categoryId = coupon.getRangeRelationId();
			MallCategory mallCategory = mallCategoryService.getById(categoryId);
			String typeStatus = mallCategory.getStatus();
			if ("0".equals(typeStatus)) {
				return Rest.failure("优惠券已经下架");
			}
		}
		
		if (rangType == 2) {
			Long goodId = coupon.getRangeRelationId();
			MallGoods mallGoods = mallGoodsService.getById(goodId);
			Integer isShelf = mallGoods.getIsShelf();
			if (0 == isShelf) {
				return Rest.failure("优惠券已经下架");
			}
		}
		
		Integer totalNum = coupon.getTotalNum();
		if (totalNum != 0 && mallCouponService.reduceCouponNum(couponId) <= 0) {
			return Rest.failure("优惠券被抢光了");
		}
		
		// 用户领券记录
		MallCouponUser couponUser = new MallCouponUser();
		couponUser.setCouponUserId(DistributedIdWorker.nextId());
		couponUser.setCouponId(couponId);
		couponUser.setUserId(userId);
		couponUser.setStatus(0);
		Integer timeType = coupon.getTimeType();
		// 设置有效期
		if (timeType == 0) {
			couponUser.setStartTime(coupon.getStartTime());
			couponUser.setEndTime(coupon.getEndTime());
		} else {
			couponUser.setStartTime(DateUtils.getNowDate());
			couponUser.setEndTime(DateUtils.getAfterDay(new Date(), coupon.getDays()));
		}
		// 向领劵表中插入数据
		if (mallCouponUserService.save(couponUser)) {
			return Rest.ok();
		} else {
			return Rest.failure();
		}

	}

	@GetMapping("/mylist")
	public Rest myCouponList(@LoginUser Long userId, Integer page, Integer limit, Integer status) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		Page<MallCouponUser> pages = this.getPage(page, limit);
		QueryWrapper<MallCouponUser> ew = new QueryWrapper<MallCouponUser>();
		ew.eq("status", status);
		ew.eq("user_id", userId);
		IPage<MallCouponUser> pageData = mallCouponUserService.page(pages, ew);
		List<MallCouponUser> couponUserList = pageData.getRecords();
		Map<Long, MallCouponUser> couponMaps = couponUserList.stream().collect(Collectors.toMap(MallCouponUser::getCouponId, m -> m));
		List<MallCoupon> couponList = new ArrayList<>();
		if (!StringUtils.isEmpty(couponMaps)) {
			couponList = mallCouponService.list(new QueryWrapper<MallCoupon>().in("coupon_id", couponMaps.keySet()));
		}
		Iterator<MallCoupon> couponIterator = couponList.iterator();
		while (couponIterator.hasNext()) {
			MallCoupon coupon = couponIterator.next();
			Long couponId = coupon.getCouponId();
			MallCouponUser couponUser = couponMaps.get(couponId);
			coupon.setStartTime(couponUser.getStartTime());
			coupon.setEndTime(couponUser.getEndTime());
			Integer rangeType = coupon.getRangeType();
			if (rangeType == 1) {
				coupon.setRangeRelationName("仅限（" + coupon.getRangeRelationName() + "）类别使用");
			} else if (rangeType == 2) {
				coupon.setRangeRelationName("仅限（" + coupon.getRangeRelationName() + "）使用");
			}
		}
		data.put("list", couponList);
		data.put("total", couponList.size());
		return Rest.okData(data);
	}

	/**
	 * 当前购物车下单商品订单可用优惠券
	 *
	 * @param userId
	 * @param cartId
	 * @param grouponRulesId
	 * @return
	 */
	@GetMapping("/selectlist")
	public Rest selectlist(@LoginUser Long userId, MallCart cart) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		List<MallCoupon> aviliableCoupon = mallCouponService.checkedCounpon(userId, cart);
		Map<String, Object> data = new HashMap<>();
		data.put("availableCoupon", aviliableCoupon);
		data.put("availableCouponLength", aviliableCoupon.size());
		return Rest.okData(data);
	}

}

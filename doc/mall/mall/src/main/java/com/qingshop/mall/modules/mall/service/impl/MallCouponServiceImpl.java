package com.qingshop.mall.modules.mall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallCouponUser;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.mapper.MallCouponMapper;
import com.qingshop.mall.modules.mall.service.IMallCartService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallCouponUserService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;

/**
 * 店家优惠券 服务实现类
 */
@Service
public class MallCouponServiceImpl extends ServiceImpl<MallCouponMapper, MallCoupon> implements IMallCouponService {

	@Autowired
	private IMallCartService mallCartService;

	@Autowired
	private IMallCouponUserService mallCouponUserService;

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallGoodsService mallGoodsService;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;

	@Override
	public List<MallCoupon> checkedCounpon(Long userId, MallCart cart) {
		Long cartId = cart.getCartId();
		// 商品价格
		List<MallCart> cartsList = null;
		// 立即购买
		if (cartId == 0) {
			cartsList = new ArrayList<>(1);
			cartsList.add(cart);
		} else {
			cartsList = mallCartService.list(new QueryWrapper<MallCart>().eq("user_id", userId));
		}
		// 筛选出可用的优惠券
		QueryWrapper<MallCouponUser> ew = new QueryWrapper<MallCouponUser>();
		ew.eq("user_id", userId);
		ew.eq("status", 0);
		ew.lt("start_time", new Date());
		ew.gt("end_time", new Date());
		List<MallCoupon> availableCoupon = new ArrayList<>();
		List<MallCouponUser> couponUserList = mallCouponUserService.list(ew);
		List<Long> couponids = couponUserList.stream().map(m -> m.getCouponId()).collect(Collectors.toList());
		List<MallCoupon> couponList = new ArrayList<>();
		if (!StringUtils.isEmpty(couponids)) {
			couponList = (List<MallCoupon>) mallCouponService.listByIds(couponids);
		}
		if (!StringUtils.isEmpty(couponList)) {
			Map<Long, MallCouponUser> couponUserMaps = couponUserList.stream().collect(Collectors.toMap(MallCouponUser::getCouponId, m -> m));
			BigDecimal goodsTotalPrice = new BigDecimal(0.00);
			Map<Long, BigDecimal> goodMap = new HashMap<>();
			Map<Long, BigDecimal> categoryMap = new HashMap<>();
			for (MallCart mallCart : cartsList) {
				Long goodId = mallCart.getGoodsId();
				MallGoods mallGoods = mallGoodsService.getById(goodId);
				Long categoryId = mallGoods.getCategoryId();
				Long skudetailId = mallCart.getSkudetailId();
				MallGoodsSkudetail mallGoodsSkudetail = mallGoodsSkudetailService.getById(skudetailId);
				BigDecimal goodPrice = mallGoodsSkudetail.getGoodPrice().multiply(new BigDecimal(mallCart.getNumber()));
				goodsTotalPrice = goodsTotalPrice.add(goodPrice);
				if (goodMap.containsKey(goodId)) {
					goodMap.put(goodId, goodPrice.add(goodMap.get(goodId)));
				} else {
					goodMap.put(goodId, goodPrice);
				}
				if (categoryMap.containsKey(categoryId)) {
					categoryMap.put(categoryId, goodPrice.add(categoryMap.get(categoryId)));
				} else {
					categoryMap.put(categoryId, goodPrice);
				}
			}
			for (MallCoupon mallCoupon : couponList) {
				Integer rangType = mallCoupon.getRangeType();
				Long rangeRelationId = mallCoupon.getRangeRelationId();
				BigDecimal limit = new BigDecimal(mallCoupon.getConditionNum());
				if (rangType == 0 && limit.compareTo(goodsTotalPrice) != 1) {
					availableCoupon.add(mallCoupon);
					mallCoupon.setRangeRelationName("全场通用");
				}
				if (rangType == 1 && categoryMap.containsKey(rangeRelationId) && limit.compareTo(categoryMap.get(rangeRelationId)) != 1) {
					availableCoupon.add(mallCoupon);
					mallCoupon.setRangeRelationName("仅限类别" + mallCoupon.getRangeRelationName() + "使用");
				}
				if (rangType == 2 && goodMap.containsKey(rangeRelationId) && limit.compareTo(goodMap.get(rangeRelationId)) != 1) {
					availableCoupon.add(mallCoupon);
					mallCoupon.setRangeRelationName("仅限" + mallCoupon.getRangeRelationName() + "使用");
				}
				Long couponIdKey = mallCoupon.getCouponId();
				MallCouponUser couponUser = couponUserMaps.get(couponIdKey);
				mallCoupon.setStartTime(couponUser.getStartTime());
				mallCoupon.setEndTime(couponUser.getEndTime());
			}
		}
		return availableCoupon;
	}

	@Override
	public int reduceCouponNum(Long couponId) {
		return this.baseMapper.reduceCouponNum(couponId);
	}
}

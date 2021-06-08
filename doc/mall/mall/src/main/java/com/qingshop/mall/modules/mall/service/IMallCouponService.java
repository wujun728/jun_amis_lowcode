package com.qingshop.mall.modules.mall.service;

import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.entity.MallCoupon;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 店家优惠券 服务类
 */
public interface IMallCouponService extends IService<MallCoupon> {

	List<MallCoupon> checkedCounpon(Long userId, MallCart cart);
	
	int reduceCouponNum(Long couponId);

}

package com.qingshop.mall.modules.mall.mapper;

import com.qingshop.mall.modules.mall.entity.MallCoupon;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 店家优惠券 Mapper 接口
 */
public interface MallCouponMapper extends BaseMapper<MallCoupon> {
	int reduceCouponNum(@Param("couponId") Long couponId);
}

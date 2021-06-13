package com.qingshop.mall.modules.wxapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallAd;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.service.IMallAdService;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;

import io.swagger.annotations.ApiOperation;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/wx/index")
public class WxIndexController extends BaseController {

	@Autowired
	private IMallGoodsService mallGoodsService;

	@Autowired
	private IMallAdService mallAdService;

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallCategoryService mallCategoryService;

	/**
	 * 首页数据
	 *
	 * @return 首页数据
	 */
	@ApiOperation(value = "首页数据", response = Rest.class)
	@RequestMapping("/page")
	public Rest page() {
		try {
			Map<String, Object> data = new HashMap<>();
			List<MallAd> mallAdList = mallAdService.list(null);
			Page<MallGoods> page = this.getPage(1, 4);
			// 查询分页
			QueryWrapper<MallGoods> ew = new QueryWrapper<MallGoods>();
			ew.orderByDesc("create_time");
			IPage<MallGoods> pageData = mallGoodsService.page(page, ew);
			List<MallGoods> proList = pageData.getRecords();
			Page<MallCoupon> pages = this.getPage(1, 2);
			QueryWrapper<MallCoupon> couponEw = new QueryWrapper<MallCoupon>();
			couponEw.eq("activity_status", 1);
			couponEw.orderByDesc("create_time");
			IPage<MallCoupon> couponPageData = mallCouponService.page(pages, couponEw);
			List<MallCoupon> couponList = couponPageData.getRecords();
			List<Long> categoryIdList = couponList.stream().filter(m -> m.getRangeType() == 1).map(m -> (Long) m.getRangeRelationId()).collect(Collectors.toList());
			List<MallCategory> categoryList = new ArrayList<>();
			if (!StringUtils.isEmpty(categoryIdList)) {
				categoryList = (List<MallCategory>) mallCategoryService.listByIds(categoryIdList);
			}
			Map<Long, MallCategory> goodTypeMapMap = categoryList.stream().collect(Collectors.toMap(MallCategory::getCategoryId, m -> m));
			List<Long> goodIdList = couponList.stream().filter(m -> m.getRangeType() == 2).map(m -> (Long) m.getRangeRelationId()).collect(Collectors.toList());
			List<MallGoods> goodList = new ArrayList<>();
			if (!StringUtils.isEmpty(goodIdList)) {
				goodList = (List<MallGoods>) mallGoodsService.listByIds(goodIdList);
			}
			Map<Long, MallGoods> goodMapMap = goodList.stream().collect(Collectors.toMap(MallGoods::getGoodsId, m -> m));
			Iterator<MallCoupon> couponIterator = couponList.iterator();
			while (couponIterator.hasNext()) {
				MallCoupon coupon = couponIterator.next();
				// 绝对有效期剔除过期劵
				Integer timeType = coupon.getTimeType();
				if (timeType == 0 && coupon.getEndTime().before(new Date())) {
					continue;
				}
				Integer rangeType = coupon.getRangeType();
				Long rangeRelationId = coupon.getRangeRelationId();
				if (rangeType == 1) {
					MallCategory goodTypeMap = goodTypeMapMap.get(rangeRelationId);
					if (null != goodTypeMap) {
						coupon.setRangeRelationName("仅限（" + goodTypeMap.getName() + "）类别使用");
					} else {
						couponIterator.remove();
					}
				} else if (rangeType == 2) {
					MallGoods goodMap = goodMapMap.get(rangeRelationId);
					if (null != goodMap) {
						coupon.setRangeRelationName("仅限（" + goodMap.getGoodName() + "）使用");
					} else {
						couponIterator.remove();
					}
				} else {
					coupon.setRangeRelationName("全场通用");
				}
			}

			data.put("coupon", couponList);
			data.put("items", mallAdList);
			data.put("newest", proList);
			data.put("best", proList);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("首页数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	/**
	 * 首页加载更多数据
	 *
	 * @return 首页数据
	 */
	@ApiOperation(value = "首页加载更多数据", response = Rest.class)
	@RequestMapping("/getlist")
	public Rest getlist(Integer page) {
		try {
			Map<String, Object> data = new HashMap<>();
			Page<MallGoods> pages = this.getPage(page, 4);
			QueryWrapper<MallGoods> ew = new QueryWrapper<MallGoods>();
			ew.orderByDesc("create_time");
			IPage<MallGoods> pageData = mallGoodsService.page(pages, ew);
			List<MallGoods> proList = pageData.getRecords();
			data.put("prolist", proList);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("首页数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

}
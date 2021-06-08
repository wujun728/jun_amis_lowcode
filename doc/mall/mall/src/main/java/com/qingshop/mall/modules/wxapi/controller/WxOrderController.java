package com.qingshop.mall.modules.wxapi.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.IpUtils;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.common.utils.text.Convert;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallAddress;
import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallCouponUser;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.entity.MallOrder;
import com.qingshop.mall.modules.mall.entity.MallOrderDetail;
import com.qingshop.mall.modules.mall.entity.MallOrderSerial;
import com.qingshop.mall.modules.mall.entity.MallRegion;
import com.qingshop.mall.modules.mall.entity.MallSaleArea;
import com.qingshop.mall.modules.mall.entity.MallShipFree;
import com.qingshop.mall.modules.mall.entity.MallShipRule;
import com.qingshop.mall.modules.mall.entity.MallShipSet;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallAddressService;
import com.qingshop.mall.modules.mall.service.IMallCartService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallCouponUserService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;
import com.qingshop.mall.modules.mall.service.IMallOrderDetailService;
import com.qingshop.mall.modules.mall.service.IMallOrderSerialService;
import com.qingshop.mall.modules.mall.service.IMallOrderService;
import com.qingshop.mall.modules.mall.service.IMallRegionService;
import com.qingshop.mall.modules.mall.service.IMallSaleAreaService;
import com.qingshop.mall.modules.mall.service.IMallShipFreeService;
import com.qingshop.mall.modules.mall.service.IMallShipRuleService;
import com.qingshop.mall.modules.mall.service.IMallShipSetService;
import com.qingshop.mall.modules.mall.service.IMallUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/order")
public class WxOrderController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(WxOrderController.class);

	@Autowired
	private IMallGoodsService mallGoodService;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;

	@Autowired
	private IMallAddressService mallAddressService;

	@Autowired
	private IMallCartService mallCartService;

	@Autowired
	private IMallRegionService mallRegionService;

	@Autowired
	private IMallOrderService mallOrderService;

	@Autowired
	private IMallOrderDetailService mallOrderDetailService;

	@Autowired
	private IMallUserService mallUserService;

	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private IMallOrderSerialService mallOrderSerialService;

	@Autowired
	private IMallShipSetService mallShipSetService;

	@Autowired
	private IMallSaleAreaService mallSaleAreaService;

	@Autowired
	private IMallShipFreeService mallShipFreeService;

	@Autowired
	private IMallShipRuleService mallShipRuleService;

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallCouponUserService mallCouponUserService;

	@ApiOperation(value = "订单列表数据", response = Rest.class)
	@GetMapping("/lists")
	public Rest index(@LoginUser Long userId, String dataType) {
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		QueryWrapper<MallOrder> ew = new QueryWrapper<MallOrder>();
		switch (dataType) {
		case "payment":
			ew.eq("order_status", 1);
			ew.eq("pay_status", 0);
			break;
		case "delivery":
			ew.eq("order_status", 1);
			ew.eq("pay_status", 1);
			ew.eq("ship_status", 0);
			break;
		case "received":
			ew.eq("order_status", 1);
			ew.eq("pay_status", 1);
			ew.eq("ship_status", 1);
			break;
		case "completed":
			ew.eq("pay_status", 1);
			ew.eq("ship_status", 2);
			break;
		default:
			break;
		}
		ew.eq("user_id", userId).orderByDesc("create_time");
		List<MallOrder> orderList = mallOrderService.list(ew);
		for (MallOrder mallOrder : orderList) {
			List<MallOrderDetail> details = mallOrderDetailService.list(new QueryWrapper<MallOrderDetail>().eq("order_id", mallOrder.getOrderId()));
			mallOrder.setGoods(details);
		}
		result.put("list", orderList);
		return Rest.okData(result);
	}

	/**
	 * 订单商品详情
	 * 
	 * @return 详情数据
	 */
	@ApiOperation(value = "订单商品详情", response = Rest.class)
	@RequestMapping("/detail")
	public Rest detail(@LoginUser Long userId, Long orderId) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			MallOrder mallOrder = mallOrderService.getById(orderId);
			List<MallOrderDetail> goods = mallOrderDetailService.list(new QueryWrapper<MallOrderDetail>().eq("order_id", mallOrder.getOrderId()));
			mallOrder.setGoods(goods);
			data.put("order", mallOrder);
			return Rest.okData(data);
		} catch (Exception e) {
			log.error("商品详情数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	/**
	 * 立即购买
	 */
	@ApiOperation(value = "立即购买", response = Rest.class)
	@GetMapping("/buyNow")
	public Rest buyNow(@LoginUser Long userId, MallCart cart, Long couponId) {

		Map<String, Object> data = new HashMap<>();
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		BigDecimal couponPrice = new BigDecimal(0.00);
		if (couponId == null || couponId == -1L) {
			couponId = -1L;
		} else {
			MallCoupon coupon = mallCouponService.getById(couponId);
			couponPrice = coupon.getDiscount();
		}
		if (cart == null) {
			return Rest.failure();
		}
		Long skudetailId = cart.getSkudetailId();
		Integer number = cart.getNumber();
		Long goodsId = cart.getGoodsId();
		if (!ObjectUtils.allNotNull(skudetailId, number, goodsId)) {
			return Rest.failure();
		}
		if (number <= 0) {
			return Rest.failure();
		}
		// 判断商品是否可以购买
		MallGoods goods = mallGoodService.getById(goodsId);
		if (goods == null || goods.getIsShelf() != 1) {
			return Rest.failure("商品已下架");
		}
		// 取得规格的信息,判断规格库存
		MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(skudetailId);
		if (skudetail == null || number > skudetail.getNumber()) {
			return Rest.failure("库存不足");
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
		Map<Long, MallCouponUser> couponUserMaps = couponUserList.stream().collect(Collectors.toMap(MallCouponUser::getCouponId, m -> m));

		BigDecimal goodsAmount = new BigDecimal(0.00);
		BigDecimal expressPrice = new BigDecimal(0.00);
		goodsAmount = skudetail.getGoodPrice().multiply(new BigDecimal(number));

		List<MallCart> goods_list = new ArrayList<>();
		MallCart buyNowCart = new MallCart();
		buyNowCart.setGoodsId(goodsId);
		buyNowCart.setPicUrl(goods.getPicUrl());
		buyNowCart.setNumber(number);
		buyNowCart.setGoodPrice(skudetail.getGoodPrice());
		buyNowCart.setGoodsName(goods.getGoodName());
		buyNowCart.setSpecifications(skudetail.getSkudetails());
		goods_list.add(buyNowCart);
		// 优惠券过滤
		MallGoods good = mallGoodService.getById(goodsId);
		Long categoryId = good.getCategoryId();
		for (MallCoupon mallCoupon : couponList) {
			Integer rangType = mallCoupon.getRangeType();
			Long rangeRelationId = mallCoupon.getRangeRelationId();
			BigDecimal limit = new BigDecimal(mallCoupon.getConditionNum());
			if (rangType == 0 && limit.compareTo(goodsAmount) != 1) {
				availableCoupon.add(mallCoupon);
				mallCoupon.setRangeRelationName("全场通用");
			}
			if (rangType == 1 && goodsId == rangeRelationId && limit.compareTo(goodsAmount) != 1) {
				availableCoupon.add(mallCoupon);
				mallCoupon.setRangeRelationName("仅限类别" + mallCoupon.getRangeRelationName() + "使用");
			}
			if (rangType == 2 && categoryId == rangeRelationId && limit.compareTo(goodsAmount) != 1) {
				availableCoupon.add(mallCoupon);
				mallCoupon.setRangeRelationName("仅限" + mallCoupon.getRangeRelationName() + "使用");
			}
			Long couponIdKey = mallCoupon.getCouponId();
			MallCouponUser couponUser = couponUserMaps.get(couponIdKey);
			mallCoupon.setStartTime(couponUser.getStartTime());
			mallCoupon.setEndTime(couponUser.getEndTime());
		}
		// 查询默认收货地址
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		params.put("is_default", 1);
		MallAddress address = mallAddressService.getOne(new QueryWrapper<MallAddress>().allEq(params));
		List<MallAddress> addressList = mallAddressService.list(new QueryWrapper<MallAddress>().eq("user_id", userId));
		if (addressList.size() > 0) {
			data.put("exist_address", true);
		}
		if (ObjectUtils.allNotNull(address)) {
			// 校验当前地址是否支持配送
			String cityName = address.getCity();
			Map<String, Object> regionParams = new HashMap<String, Object>();
			regionParams.put("name", cityName);
			regionParams.put("level", 2);
			MallRegion mallRegion = mallRegionService.getOne(new QueryWrapper<MallRegion>().allEq(regionParams));
			String cityId = mallRegion.getId().toString();
			MallShipSet shipSet = mallShipSetService.getOne(null);
			// 销售区域
			Integer saleAreaType = shipSet.getSaleAreaType();
			if (saleAreaType == 0) {
				List<MallSaleArea> saleAreaList = mallSaleAreaService.list(null);
				List<String> saleIdsList = new ArrayList<String>();
				for (MallSaleArea mallSaleArea : saleAreaList) {
					String[] saleIds = Convert.toStrArray(mallSaleArea.getSaleIds());
					saleIdsList.addAll(Arrays.asList(saleIds));
				}
				if (!saleIdsList.contains(cityId)) {
					data.put("intra_region", true);
					data.put("express_price", expressPrice);
					data.put("order_pay_price", "");
					data.put("goods_list", goods_list);
					data.put("address", "");
					data.put("order_total_num", number);
					data.put("order_total_price", goodsAmount);
					data.put("couponId", couponId);
					data.put("couponPrice", couponPrice);
					data.put("availableCouponLength", availableCoupon.size());
					data.put("availableCoupon", availableCoupon);
					return new Rest(301, 0, data, "该区域不支持配送");
				}
			}
			// 自提快递
			Integer isSelfMention = shipSet.getIsSelfMention();
			if (isSelfMention == 1) {
				data.put("intra_region", true);
				data.put("express_price", expressPrice + "(用户自提费用)");
				data.put("order_pay_price", goodsAmount.add(expressPrice).subtract(couponPrice));
				data.put("goods_list", goods_list);
				data.put("address", address);
				data.put("order_total_num", number);
				data.put("order_total_price", goodsAmount);
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}
			Integer freightType = shipSet.getFreightType();
			if (freightType == 1) {
				data.put("intra_region", true);
				data.put("express_price", expressPrice);
				data.put("order_pay_price", goodsAmount.add(expressPrice).subtract(couponPrice));
				data.put("goods_list", goods_list);
				data.put("address", address);
				data.put("order_total_num", number);
				data.put("order_total_price", goodsAmount);
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}

			// 快递校验是否满足包邮条件
			List<MallShipFree> shipfreeList = mallShipFreeService.list(null);
			boolean isFreeship = false;
			for (MallShipFree shipfree : shipfreeList) {
				String[] shipfreeIds = Convert.toStrArray(shipfree.getFreeshipIds());
				long count = Arrays.stream(shipfreeIds).filter(str -> str.equals(cityId)).count();
				if (count > 0) {
					if (!(shipfree.getWeightFree().compareTo(skudetail.getGoodWeight()) == -1) || !(shipfree.getExpenseFee().compareTo(skudetail.getGoodPrice()) == -1)) {
						isFreeship = true;
						break;
					}
				}
			}
			// 包邮
			if (isFreeship) {
				data.put("intra_region", true);
				data.put("express_price", expressPrice);
				data.put("order_pay_price", goodsAmount.add(expressPrice).subtract(couponPrice));
				data.put("goods_list", goods_list);
				data.put("address", address);
				data.put("order_total_num", number);
				data.put("order_total_price", goodsAmount);
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}

			// 计算自定义运费
			BigDecimal defaultWeight = shipSet.getDefaultWeight();
			BigDecimal defaultExpense = shipSet.getDefaultExpense();
			BigDecimal continueWeight = new BigDecimal(1.00);
			BigDecimal continueExpense = shipSet.getContinueExpense();
			List<MallShipRule> shipRuleList = mallShipRuleService.list(null);
			for (MallShipRule shipRule : shipRuleList) {
				String[] shipRuleIds = Convert.toStrArray(shipRule.getShipIds());
				long count = Arrays.stream(shipRuleIds).filter(str -> str.equals(cityId)).count();
				if (count > 0) {
					defaultWeight = shipRule.getDefaultWeight();
					defaultExpense = shipRule.getDefaultExpense();
					continueWeight = shipRule.getContinueWeight();
					continueExpense = shipRule.getContinueExpense();
					break;
				}
			}
			BigDecimal weight = skudetail.getGoodWeight();
			// 重量小于等于首重
			if (!(weight.compareTo(defaultWeight) == 1)) {
				data.put("express_price", defaultExpense);
				data.put("order_pay_price", goodsAmount.add(defaultExpense).subtract(couponPrice));
			} else {
				BigDecimal totalAdditional = weight.subtract(defaultWeight).divide(continueWeight);
				BigDecimal totalAdditionalFee = defaultExpense.add(totalAdditional.multiply(continueExpense));
				data.put("express_price", totalAdditionalFee);
				data.put("order_pay_price", goodsAmount.add(totalAdditionalFee).subtract(couponPrice));
			}
			data.put("intra_region", true);
		} else {
			data.put("intra_region", false);
		}
		data.put("goods_list", goods_list);
		data.put("address", address);
		data.put("order_total_num", number);
		data.put("order_total_price", goodsAmount);
		data.put("couponId", couponId);
		data.put("couponPrice", couponPrice);
		data.put("availableCouponLength", availableCoupon.size());
		data.put("availableCoupon", availableCoupon);
		return Rest.okData(data);
	}

	/**
	 * 立即购买下单
	 */
	@ApiOperation(value = "立即购买下单", response = Rest.class)
	@PostMapping("/addOrderBuyNow")
	@Transactional
	public Rest addOrderBuyNow(@RequestBody String body, HttpServletRequest request, @LoginUser Long userId) {

		Map<String, Object> data = new HashMap<>();
		JSONObject json = JSONObject.parseObject(body);
		String skudetailId = json.getString("skudetailId");
		Integer number = json.getInteger("number");
		String goodsId = json.getString("goodsId");
		Long couponId = json.getLong("couponId");
		if (couponId == null) {
			return Rest.failure("优惠券不可使用");
		}
		BigDecimal couponPrice = new BigDecimal(0.00);
		if (couponId != -1L) {
			MallCoupon coupon = mallCouponService.getById(couponId);
			couponPrice = coupon.getDiscount();
		}
		if (!ObjectUtils.allNotNull(skudetailId, number, goodsId)) {
			return Rest.failure();
		}
		if (number <= 0) {
			return Rest.failure();
		}
		// 判断商品是否可以购买
		MallGoods goods = mallGoodService.getById(goodsId);
		if (goods == null || goods.getIsShelf() != 1) {
			return Rest.failure("商品已下架");
		}
		// 取得规格的信息,判断规格库存
		MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(skudetailId);
		if (skudetail == null || number > skudetail.getNumber()) {
			return Rest.failure("库存不足，购买失败！");
		}
		BigDecimal goodsAmount = new BigDecimal(0.00);
		BigDecimal expressPrice = new BigDecimal(0.00);
		goodsAmount = skudetail.getGoodPrice().multiply(new BigDecimal(number));
		// 查询默认收货地址
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		params.put("is_default", 1);
		MallAddress address = mallAddressService.getOne(new QueryWrapper<MallAddress>().allEq(params));
		if (ObjectUtils.allNotNull(address)) {
			// 校验当前地址是否支持配送
			String cityName = address.getCity();
			Map<String, Object> regionParams = new HashMap<String, Object>();
			regionParams.put("name", cityName);
			regionParams.put("level", 2);
			MallRegion mallRegion = mallRegionService.getOne(new QueryWrapper<MallRegion>().allEq(regionParams));
			String cityId = mallRegion.getId().toString();
			MallShipSet shipSet = mallShipSetService.getOne(null);
			// 销售区域
			Integer saleAreaType = shipSet.getSaleAreaType();
			if (saleAreaType == 0) {
				List<MallSaleArea> saleAreaList = mallSaleAreaService.list(null);
				List<String> saleIdsList = new ArrayList<String>();
				for (MallSaleArea mallSaleArea : saleAreaList) {
					String[] saleIds = Convert.toStrArray(mallSaleArea.getSaleIds());
					saleIdsList.addAll(Arrays.asList(saleIds));
				}
				if (!saleIdsList.contains(cityId)) {
					return Rest.failure("该区域不支持配送");
				}
			}
			// 自提快递
			Integer isSelfMention = shipSet.getIsSelfMention();
			if (isSelfMention == 1) {
				expressPrice = new BigDecimal(0.00);
			}
			Integer freightType = shipSet.getFreightType();
			if (freightType == 1) {
				expressPrice = new BigDecimal(0.00);
			}

			if (isSelfMention == 0 && freightType == 0) {
				// 快递校验是否满足包邮条件
				List<MallShipFree> shipfreeList = mallShipFreeService.list(null);
				boolean isFreeship = false;
				for (MallShipFree shipfree : shipfreeList) {
					String[] shipfreeIds = Convert.toStrArray(shipfree.getFreeshipIds());
					long count = Arrays.stream(shipfreeIds).filter(str -> str.equals(cityId)).count();
					if (count > 0) {
						if (!(shipfree.getWeightFree().compareTo(skudetail.getGoodWeight()) == -1) || !(shipfree.getExpenseFee().compareTo(skudetail.getGoodPrice()) == -1)) {
							isFreeship = true;
							break;
						}
					}
				}
				if (isFreeship) { // 包邮
					expressPrice = new BigDecimal(0.00);
				} else { // 计算自定义运费
					BigDecimal defaultWeight = shipSet.getDefaultWeight();
					BigDecimal defaultExpense = shipSet.getDefaultExpense();
					BigDecimal continueWeight = new BigDecimal(1.00);
					BigDecimal continueExpense = shipSet.getContinueExpense();
					List<MallShipRule> shipRuleList = mallShipRuleService.list(null);
					for (MallShipRule shipRule : shipRuleList) {
						String[] shipRuleIds = Convert.toStrArray(shipRule.getShipIds());
						long count = Arrays.stream(shipRuleIds).filter(str -> str.equals(cityId)).count();
						if (count > 0) {
							defaultWeight = shipRule.getDefaultWeight();
							defaultExpense = shipRule.getDefaultExpense();
							continueWeight = shipRule.getContinueWeight();
							continueExpense = shipRule.getContinueExpense();
							break;
						}
					}

					BigDecimal weight = skudetail.getGoodWeight();
					// 重量小于等于首重
					if (!(weight.compareTo(defaultWeight) == 1)) {
						expressPrice = defaultExpense;
					} else {
						BigDecimal totalAdditional = weight.subtract(defaultWeight).divide(continueWeight);
						BigDecimal totalAdditionalFee = defaultExpense.add(totalAdditional.multiply(continueExpense));
						expressPrice = totalAdditionalFee;
					}
				}
			}
		} else {
			return Rest.failure("请先选择收货地址");
		}
		// 冻结库存
		if (mallGoodsSkudetailService.reduceStock(skudetail.getSkudetailId(), number) <= 0) {
			return Rest.failure("库存不足，购买失败！");
		}
		// 生成订单
		MallOrder mallOrder = new MallOrder();
		mallOrder.setOrderId(DistributedIdWorker.nextId());
		mallOrder.setUserId(userId);
		mallOrder.setOrderSn(Long.valueOf(this.getSerial("order_no")));
		mallOrder.setOrderStatus(1);
		mallOrder.setPayStatus(0);
		mallOrder.setShipStatus(0);
		mallOrder.setConsignee(address.getName());
		mallOrder.setMobile(address.getPhone());
		String consigerAddress = address.getProvince() + address.getCity() + address.getRegion() + address.getDetail();
		mallOrder.setAddress(consigerAddress);
		mallOrder.setTotalPrice(goodsAmount);
		mallOrder.setShipPrice(expressPrice);
		mallOrder.setCouponPrice(couponPrice);
		mallOrder.setOrderPrice(goodsAmount.add(expressPrice).subtract(couponPrice));
		if (mallOrderService.save(mallOrder)) {
			MallOrderDetail mallOrderDetail = new MallOrderDetail();
			mallOrderDetail.setOrderDetailId(DistributedIdWorker.nextId());
			mallOrderDetail.setOrderId(mallOrder.getOrderId());
			mallOrderDetail.setGoodsId(goods.getGoodsId());
			mallOrderDetail.setGoodsName(goods.getGoodName());
			mallOrderDetail.setPicUrl(goods.getPicUrl());
			mallOrderDetail.setSkuDetailId(skudetail.getSkudetailId());
			mallOrderDetail.setGoodPrice(skudetail.getGoodPrice());
			mallOrderDetail.setGoodsSn(skudetail.getBusinessCode());
			mallOrderDetail.setNumber(number);
			mallOrderDetail.setSpecifications(skudetail.getSkudetails());
			mallOrderDetail.setComment(0L);
			mallOrderDetailService.save(mallOrderDetail);
			// 使用优惠券则更新优惠券状态
			if (couponId != -1L) {
				MallCouponUser couponUser = new MallCouponUser();
				couponUser.setOrderId(mallOrder.getOrderId());
				couponUser.setUsedTime(new Date());
				couponUser.setStatus(1);
				mallCouponUserService.update(couponUser, new QueryWrapper<MallCouponUser>().eq("user_id", userId).eq("coupon_id", couponId));
			}
		}
		// 微信支付
		MallUser user = mallUserService.getById(userId);
		WxPayMpOrderResult result = null;
		try {
			WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
			orderRequest.setOutTradeNo(String.valueOf(mallOrder.getOrderSn()));
			orderRequest.setOpenid(user.getWeixinOpenid());
			orderRequest.setBody("订单：" + mallOrder.getOrderSn());
			// 元转成分
			int fee = 0;
			BigDecimal actualPrice = mallOrder.getTotalPrice().subtract(mallOrder.getShipPrice());
			fee = actualPrice.multiply(new BigDecimal(100)).intValue();
			orderRequest.setTotalFee(fee);
			orderRequest.setSpbillCreateIp(IpUtils.getIpAddr(request));
			result = wxPayService.createOrder(orderRequest);
			data.put("payment", result);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> resultStr = new HashMap<>();
			resultStr.put("timeStamp", "");
			resultStr.put("nonceStr", "");
			resultStr.put("packageValue", "");
			resultStr.put("paySign", "");
			data.put("payment", resultStr);
		}
		return Rest.okData(data);
	}

	/**
	 * 购物车购买
	 */
	@ApiOperation(value = "购物车购买", response = Rest.class)
	@GetMapping("/cart")
	public Rest cart(@LoginUser Long userId, Long couponId) {
		Map<String, Object> data = new HashMap<>();
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		BigDecimal couponPrice = new BigDecimal(0.00);
		if (couponId == null || couponId == -1L) {
			couponId = -1L;
		} else {
			MallCoupon coupon = mallCouponService.getById(couponId);
			couponPrice = coupon.getDiscount();
		}
		// 查询默认收货地址
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		params.put("is_default", 1);
		MallAddress address = mallAddressService.getOne(new QueryWrapper<MallAddress>().allEq(params));
		List<MallAddress> addressList = mallAddressService.list(new QueryWrapper<MallAddress>().eq("user_id", userId));
		List<MallCart> cartList = mallCartService.list(new QueryWrapper<MallCart>().eq("user_id", userId));
		// 筛选出可用的优惠券
		QueryWrapper<MallCouponUser> ew = new QueryWrapper<MallCouponUser>();
		ew.eq("user_id", userId);
		ew.eq("status", 0);
		ew.lt("start_time", new Date());
		ew.gt("end_time", new Date());
		List<MallCoupon> availableCoupon = new ArrayList<>();
		List<MallCouponUser> couponUserList = mallCouponUserService.list(ew);
		List<Long> couponIds = couponUserList.stream().map(m -> m.getCouponId()).collect(Collectors.toList());
		List<MallCoupon> couponList = new ArrayList<>();
		if (!StringUtils.isEmpty(couponIds)) {
			couponList = (List<MallCoupon>) mallCouponService.listByIds(couponIds);
		}
		Map<Long, MallCouponUser> couponUserMaps = couponUserList.stream().collect(Collectors.toMap(MallCouponUser::getCouponId, m -> m));
		Integer goodsCount = 0;
		BigDecimal goodsAmount = new BigDecimal(0.00);
		BigDecimal expressPrice = new BigDecimal(0.00);
		BigDecimal totalWeight = new BigDecimal(0.00);
		// 商品优惠价格过滤
		Map<Long, BigDecimal> goodMap = new HashMap<>();
		// 商品分类优惠价格过滤
		Map<Long, BigDecimal> categoryMap = new HashMap<>();
		for (MallCart cart : cartList) {
			// 判断商品是否可以购买
			MallGoods goods = mallGoodService.getById(cart.getGoodsId());
			if (goods == null || goods.getIsShelf() != 1) {
				return Rest.failure("商品已下架");
			}
			MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(cart.getSkudetailId());
			// 取得规格的信息,判断规格库存
			if (skudetail == null || cart.getNumber() > skudetail.getNumber()) {
				return Rest.failure("库存不足，购买失败！");
			}
			goodsCount += cart.getNumber();
			BigDecimal goodPrice = skudetail.getGoodPrice().multiply(new BigDecimal(cart.getNumber()));
			goodsAmount = goodsAmount.add(goodPrice);
			totalWeight = totalWeight.add(skudetail.getGoodWeight().multiply(new BigDecimal(cart.getNumber())));
			cart.setPicUrl(goods.getPicUrl());
			cart.setGoodPrice(skudetail.getGoodPrice());
			cart.setGoodsName(goods.getGoodName());
			cart.setSpecifications(skudetail.getSkudetails());
			Long goodId = goods.getGoodsId();
			Long categoryId = goods.getCategoryId();
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
			if (rangType == 0 && limit.compareTo(goodsAmount) != 1) {
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

		if (addressList.size() > 0) {
			data.put("exist_address", true);
		}
		if (ObjectUtils.allNotNull(address)) {
			// 校验当前地址是否支持配送
			String cityName = address.getCity();
			Map<String, Object> regionParams = new HashMap<String, Object>();
			regionParams.put("name", cityName);
			regionParams.put("level", 2);
			MallRegion mallRegion = mallRegionService.getOne(new QueryWrapper<MallRegion>().allEq(regionParams));
			String cityId = mallRegion.getId().toString();
			MallShipSet shipSet = mallShipSetService.getOne(null);
			// 销售区域
			Integer saleAreaType = shipSet.getSaleAreaType();
			if (saleAreaType == 0) {
				List<MallSaleArea> saleAreaList = mallSaleAreaService.list(null);
				List<String> saleIdsList = new ArrayList<String>();
				for (MallSaleArea mallSaleArea : saleAreaList) {
					String[] saleIds = Convert.toStrArray(mallSaleArea.getSaleIds());
					saleIdsList.addAll(Arrays.asList(saleIds));
				}
				if (!saleIdsList.contains(cityId)) {
					data.put("intra_region", true);
					data.put("express_price", 0.00);
					data.put("goods_list", cartList);
					data.put("address", "");
					data.put("order_total_num", goodsCount);
					data.put("order_total_price", goodsAmount);
					data.put("order_pay_price", "");
					data.put("couponId", couponId);
					data.put("couponPrice", couponPrice);
					data.put("availableCouponLength", availableCoupon.size());
					data.put("availableCoupon", availableCoupon);
					return new Rest(301, 0, data, "该区域不支持配送");
				}
			}
			// 自提快递
			Integer isSelfMention = shipSet.getIsSelfMention();
			if (isSelfMention == 1) {
				data.put("intra_region", true);
				data.put("express_price", 0.00 + "(用户自提费用)");
				data.put("goods_list", cartList);
				data.put("address", address);
				data.put("order_total_num", goodsCount);
				data.put("order_total_price", goodsAmount);
				data.put("order_pay_price", expressPrice.add(goodsAmount).subtract(couponPrice));
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}
			Integer freightType = shipSet.getFreightType();
			if (freightType == 1) {
				data.put("intra_region", true);
				data.put("express_price", 0.00);
				data.put("goods_list", cartList);
				data.put("address", address);
				data.put("order_total_num", goodsCount);
				data.put("order_total_price", goodsAmount);
				data.put("order_pay_price", expressPrice.add(goodsAmount).subtract(couponPrice));
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}

			// 快递校验是否满足包邮条件
			List<MallShipFree> shipfreeList = mallShipFreeService.list(null);
			boolean isFreeship = false;
			for (MallShipFree shipfree : shipfreeList) {
				String[] shipfreeIds = Convert.toStrArray(shipfree.getFreeshipIds());
				long count = Arrays.stream(shipfreeIds).filter(str -> str.equals(cityId)).count();
				if (count > 0) {
					if (!(shipfree.getWeightFree().compareTo(totalWeight) == -1) || !(shipfree.getExpenseFee().compareTo(goodsAmount) == -1)) {
						isFreeship = true;
						break;
					}
				}
			}
			// 包邮
			if (isFreeship) {
				data.put("intra_region", true);
				data.put("express_price", 0.00);
				data.put("goods_list", cartList);
				data.put("address", address);
				data.put("order_total_num", goodsCount);
				data.put("order_total_price", goodsAmount);
				data.put("order_pay_price", expressPrice.add(goodsAmount).subtract(couponPrice));
				data.put("couponId", couponId);
				data.put("couponPrice", couponPrice);
				data.put("availableCouponLength", availableCoupon.size());
				data.put("availableCoupon", availableCoupon);
				return Rest.okData(data);
			}
			// 计算自定义运费
			BigDecimal defaultWeight = shipSet.getDefaultWeight();
			BigDecimal defaultExpense = shipSet.getDefaultExpense();
			BigDecimal continueWeight = new BigDecimal(1.00);
			BigDecimal continueExpense = shipSet.getContinueExpense();
			List<MallShipRule> shipRuleList = mallShipRuleService.list(null);
			for (MallShipRule shipRule : shipRuleList) {
				String[] shipRuleIds = Convert.toStrArray(shipRule.getShipIds());
				long count = Arrays.stream(shipRuleIds).filter(str -> str.equals(cityId)).count();
				if (count > 0) {
					defaultWeight = shipRule.getDefaultWeight();
					defaultExpense = shipRule.getDefaultExpense();
					continueWeight = shipRule.getContinueWeight();
					continueExpense = shipRule.getContinueExpense();
					break;
				}
			}
			// 重量小于等于首重
			if (!(totalWeight.compareTo(defaultWeight) == 1)) {
				data.put("express_price", defaultExpense);
			} else {
				BigDecimal totalAdditional = totalWeight.subtract(defaultWeight).divide(continueWeight);
				BigDecimal totalAdditionalFee = defaultExpense.add(totalAdditional.multiply(continueExpense));
				data.put("express_price", totalAdditionalFee);
			}
			data.put("intra_region", true);
		} else {
			data.put("intra_region", false);
		}
		data.put("goods_list", cartList);
		data.put("address", address);
		data.put("order_total_num", goodsCount);
		data.put("order_total_price", goodsAmount);
		data.put("express_price", expressPrice);
		data.put("order_pay_price", goodsAmount.add(expressPrice).subtract(couponPrice));
		data.put("couponId", couponId);
		data.put("couponPrice", couponPrice);
		data.put("availableCouponLength", availableCoupon.size());
		data.put("availableCoupon", availableCoupon);
		return Rest.okData(data);
	}

	/**
	 * 购物车购买-下单
	 */
	@ApiOperation(value = "购物车购买-下单", response = Rest.class)
	@PostMapping("/addOrderFromCart")
	@Transactional
	public Rest addOrderFromCart(@LoginUser Long userId, HttpServletRequest request, @RequestBody String body) {
		Map<String, Object> data = new HashMap<>();
		try {
			if (userId == null) {
				return Rest.failure(-1, "账号未授权");
			}
			JSONObject json = JSONObject.parseObject(body);
			Long couponId = json.getLong("couponId");
			if (couponId == null) {
				return Rest.failure("优惠券不可使用");
			}
			BigDecimal couponPrice = new BigDecimal(0.00);
			if (couponId != -1L) {
				MallCoupon coupon = mallCouponService.getById(couponId);
				couponPrice = coupon.getDiscount();
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_id", userId);
			params.put("is_default", 1);
			MallAddress address = mallAddressService.getOne(new QueryWrapper<MallAddress>().allEq(params));
			List<MallCart> cartList = mallCartService.list(new QueryWrapper<MallCart>().eq("user_id", userId));
			MallUser user = mallUserService.getById(userId);
			List<MallOrderDetail> goodList = new ArrayList<>();
			Integer goodsCount = 0;
			BigDecimal goodsAmount = new BigDecimal(0.00);
			BigDecimal expressPrice = new BigDecimal(0.00);
			BigDecimal totalWeight = new BigDecimal(0.00);
			List<Long> cartIds = new ArrayList<>();
			for (MallCart cart : cartList) {
				cartIds.add(cart.getCartId());
				MallGoods goods = mallGoodService.getById(cart.getGoodsId());
				if (goods == null || goods.getIsShelf() != 1) {
					return Rest.failure("商品已下架");
				}
				MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(cart.getSkudetailId());
				if (skudetail == null || cart.getNumber() > skudetail.getNumber()) {
					return Rest.failure("商品【"+ goods.getGoodName() +"】库存不足，购买失败！");
				}
				goodsCount += cart.getNumber();
				goodsAmount = goodsAmount.add(skudetail.getGoodPrice().multiply(new BigDecimal(cart.getNumber())));
				totalWeight = totalWeight.add(skudetail.getGoodWeight().multiply(new BigDecimal(cart.getNumber())));
				MallOrderDetail mallOrderDetail = new MallOrderDetail();
				mallOrderDetail.setOrderDetailId(DistributedIdWorker.nextId());
				mallOrderDetail.setGoodsId(goods.getGoodsId());
				mallOrderDetail.setGoodsName(goods.getGoodName());
				mallOrderDetail.setPicUrl(goods.getPicUrl());
				mallOrderDetail.setSkuDetailId(skudetail.getSkudetailId());
				mallOrderDetail.setGoodPrice(skudetail.getGoodPrice());
				mallOrderDetail.setGoodsSn(skudetail.getBusinessCode());
				mallOrderDetail.setNumber(cart.getNumber());
				mallOrderDetail.setSpecifications(skudetail.getSkudetails());
				mallOrderDetail.setComment(0L);
				goodList.add(mallOrderDetail);
				// 冻结库存
				if (skudetail.getNumber() - cart.getNumber() < 0) {
					return Rest.failure("商品库存不足，购买失败！");
				}
				// 更新库存
				if (mallGoodsSkudetailService.reduceStock(skudetail.getSkudetailId(), cart.getNumber()) == 0) {
					return Rest.failure("商品库存不足，购买失败！");
				}
				;
			}
			if (ObjectUtils.allNotNull(address)) {
				// 校验当前地址是否支持配送
				String cityName = address.getCity();
				Map<String, Object> regionParams = new HashMap<String, Object>();
				regionParams.put("name", cityName);
				regionParams.put("level", 2);
				MallRegion mallRegion = mallRegionService.getOne(new QueryWrapper<MallRegion>().allEq(regionParams));
				String cityId = mallRegion.getId().toString();
				MallShipSet shipSet = mallShipSetService.getOne(null);
				// 销售区域
				Integer saleAreaType = shipSet.getSaleAreaType();
				if (saleAreaType == 0) {
					List<MallSaleArea> saleAreaList = mallSaleAreaService.list(null);
					List<String> saleIdsList = new ArrayList<String>();
					for (MallSaleArea mallSaleArea : saleAreaList) {
						String[] saleIds = Convert.toStrArray(mallSaleArea.getSaleIds());
						saleIdsList.addAll(Arrays.asList(saleIds));
					}
					if (!saleIdsList.contains(cityId)) {
						return Rest.failure("该区域不支持配送");
					}
				}
				// 自提快递
				Integer isSelfMention = shipSet.getIsSelfMention();
				if (isSelfMention == 1) {
					expressPrice = new BigDecimal(0.00);
				}
				Integer freightType = shipSet.getFreightType();
				if (freightType == 1) {
					expressPrice = new BigDecimal(0.00);
				}
				if (isSelfMention == 0 && freightType == 0) {
					// 快递校验是否满足包邮条件
					List<MallShipFree> shipfreeList = mallShipFreeService.list(null);
					boolean isFreeship = false;
					for (MallShipFree shipfree : shipfreeList) {
						String[] shipfreeIds = Convert.toStrArray(shipfree.getFreeshipIds());
						long count = Arrays.stream(shipfreeIds).filter(str -> str.equals(cityId)).count();
						if (count > 0) {
							if (!(shipfree.getWeightFree().compareTo(totalWeight) == -1) || !(shipfree.getExpenseFee().compareTo(goodsAmount) == -1)) {
								isFreeship = true;
								break;
							}
						}
					}
					// 包邮
					if (isFreeship) {
						expressPrice = new BigDecimal(0.00);
					} else {
						// 计算自定义运费
						BigDecimal defaultWeight = shipSet.getDefaultWeight();
						BigDecimal defaultExpense = shipSet.getDefaultExpense();
						BigDecimal continueWeight = new BigDecimal(1.00);
						BigDecimal continueExpense = shipSet.getContinueExpense();
						List<MallShipRule> shipRuleList = mallShipRuleService.list(null);
						for (MallShipRule shipRule : shipRuleList) {
							String[] shipRuleIds = Convert.toStrArray(shipRule.getShipIds());
							long count = Arrays.stream(shipRuleIds).filter(str -> str.equals(cityId)).count();
							if (count > 0) {
								defaultWeight = shipRule.getDefaultWeight();
								defaultExpense = shipRule.getDefaultExpense();
								continueWeight = shipRule.getContinueWeight();
								continueExpense = shipRule.getContinueExpense();
								break;
							}
						}
						// 重量小于等于首重
						if (!(totalWeight.compareTo(defaultWeight) == 1)) {
							expressPrice = defaultExpense;
						} else {
							BigDecimal totalAdditional = totalWeight.subtract(defaultWeight).divide(continueWeight);
							BigDecimal totalAdditionalFee = defaultExpense.add(totalAdditional.multiply(continueExpense));
							expressPrice = totalAdditionalFee;
						}
					}
				}
			} else {
				data.put("intra_region", false);
			}
			// 订单编号
			MallOrder mallOrder = new MallOrder();
			mallOrder.setOrderId(DistributedIdWorker.nextId());
			mallOrder.setUserId(userId);
			mallOrder.setOrderSn(Long.valueOf(this.getSerial("order_no")));
			mallOrder.setOrderStatus(1);
			mallOrder.setPayStatus(0);
			mallOrder.setShipStatus(0);
			mallOrder.setConsignee(address.getName());
			mallOrder.setMobile(address.getPhone());
			String consigerAddress = address.getProvince() + address.getCity() + address.getRegion() + address.getDetail();
			mallOrder.setAddress(consigerAddress);
			mallOrder.setTotalPrice(goodsAmount);
			mallOrder.setShipPrice(expressPrice);
			mallOrder.setCouponPrice(couponPrice);
			mallOrder.setOrderPrice(goodsAmount.add(expressPrice).subtract(couponPrice));
			// 订单生成成功
			if (mallOrderService.save(mallOrder)) {
				for (MallOrderDetail mallOrderDetail : goodList) {
					mallOrderDetail.setOrderId(mallOrder.getOrderId());
				}
				// 保存订单明细
				mallOrderDetailService.saveBatch(goodList);
				// 商品移出购物车
				mallCartService.removeByIds(cartIds);
				// 使用优惠券则更新优惠券状态
				if (couponId != -1L) {
					MallCouponUser couponUser = new MallCouponUser();
					couponUser.setOrderId(mallOrder.getOrderId());
					couponUser.setUsedTime(new Date());
					couponUser.setStatus(1);
					mallCouponUserService.update(couponUser, new QueryWrapper<MallCouponUser>().eq("user_id", userId).eq("coupon_id", couponId));
				}
			}
			WxPayMpOrderResult result = null;
			WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
			orderRequest.setOutTradeNo(String.valueOf(mallOrder.getOrderSn()));
			orderRequest.setOpenid(user.getWeixinOpenid());
			orderRequest.setBody("订单：" + mallOrder.getOrderSn());
			// 元转成分
			int fee = 0;
			BigDecimal actualPrice = mallOrder.getOrderPrice();
			fee = actualPrice.multiply(new BigDecimal(100)).intValue();
			orderRequest.setTotalFee(fee);
			orderRequest.setSpbillCreateIp(IpUtils.getIpAddr(request));
			result = wxPayService.createOrder(orderRequest);
			data.put("payment", result);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> resultStr = new HashMap<>();
			resultStr.put("timeStamp", "");
			resultStr.put("nonceStr", "");
			resultStr.put("packageValue", "");
			resultStr.put("paySign", "");
			data.put("payment", resultStr);
		}
		return Rest.okData(data);
	}

	private String getSerial(String prefix) {
		Timestamp nowDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowDateIntString = formatter.format(nowDate);
		String serialCode = prefix + "_" + nowDateIntString.substring(0, 8);
		Integer serialNo = 1;
		List<MallOrderSerial> mallSerials = mallOrderSerialService.list(new QueryWrapper<MallOrderSerial>().eq("serial_code", serialCode));
		if (StringUtils.isNotEmpty(mallSerials)) {
			MallOrderSerial mallSerial = mallSerials.get(0);
			serialNo = (int) (mallSerial.getSerialNo() + 1);
			mallSerial.setSerialNo(mallSerial.getSerialNo() + 1);
			mallOrderSerialService.updateById(mallSerial);
		} else {
			MallOrderSerial mallSerial = new MallOrderSerial();
			mallSerial.setSerialId(DistributedIdWorker.nextId());
			mallSerial.setSerialCode(serialCode);
			mallSerial.setSerialNo(1);
			mallOrderSerialService.save(mallSerial);
		}
		String maxSerialNo = "";
		if (1 == serialNo.toString().length()) {
			maxSerialNo = "00000" + serialNo;
		} else if (2 == serialNo.toString().length()) {
			maxSerialNo = "0000" + serialNo;
		} else if (3 == serialNo.toString().length()) {
			maxSerialNo = "000" + serialNo;
		} else if (4 == serialNo.toString().length()) {
			maxSerialNo = "00" + serialNo;
		} else if (5 == serialNo.toString().length()) {
			maxSerialNo = "0" + serialNo;
		} else if (6 == serialNo.toString().length()) {
			maxSerialNo = serialNo.toString();
		}
		return nowDateIntString.substring(0, 8) + maxSerialNo + createBookingCode();
	}

	/**
	 * 取消订单
	 */
	@ApiOperation(value = "取消订单", response = Rest.class)
	@PostMapping("/cancelOrder")
	@Transactional
	public Rest cancelOrder(@RequestBody String body, @LoginUser Long userId) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		JSONObject json = JSONObject.parseObject(body);
		String orderId = json.getString("orderId");
		if ("".equals(orderId)) {
			return Rest.failure("操作失败");
		}
		MallOrder order = new MallOrder();
		order.setOrderId(Long.valueOf(orderId));
		order.setOrderStatus(0);
		if (mallOrderService.updateById(order)) {
			List<MallOrderDetail> orderDetail = mallOrderDetailService.list(new QueryWrapper<MallOrderDetail>().eq("order_id", orderId));
			if (!StringUtils.isEmpty(orderDetail)) {
				Map<Long, Integer> orderSkudetailMap = orderDetail.stream().collect(Collectors.toMap(MallOrderDetail::getSkuDetailId, MallOrderDetail::getNumber));
				List<MallGoodsSkudetail> skuDetail = mallGoodsSkudetailService.list(new QueryWrapper<MallGoodsSkudetail>().in("skudetail_id", orderSkudetailMap.keySet()));
				for (MallGoodsSkudetail mallGoodsSkudetail : skuDetail) {
					Long skuDetailId = mallGoodsSkudetail.getSkudetailId();
					Integer orderSkuNumber = orderSkudetailMap.get(skuDetailId);
					// 归还库存
					if(mallGoodsSkudetailService.addStock(skuDetailId, orderSkuNumber) == 0) {
						throw new RuntimeException("商品货品库存增加失败");
					}
				}
				List<MallCouponUser> mallCouponUserList = mallCouponUserService.list(new QueryWrapper<MallCouponUser>().eq("user_id", userId).eq("order_id", orderId));
				if(mallCouponUserList.size() > 0) {
					//归还优惠券
					for (MallCouponUser mallCouponUser : mallCouponUserList) {
						mallCouponUser.setOrderId(null);
						mallCouponUser.setUsedTime(null);
						mallCouponUser.setStatus(0);
					}
					mallCouponUserService.updateBatchById(mallCouponUserList);
				}
			}
			return Rest.ok();
		} else {
			return Rest.failure("操作失败");
		}
	}

	/**
	 * 确认收货
	 */
	@ApiOperation(value = "确认收货", response = Rest.class)
	@PostMapping("/receipt")
	public Rest receipt(@RequestBody String body, @LoginUser Long userId) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		JSONObject json = JSONObject.parseObject(body);
		String orderId = json.getString("orderId");
		if ("".equals(orderId)) {
			return Rest.failure("操作失败");
		}
		MallOrder order = new MallOrder();
		order.setOrderId(Long.valueOf(orderId));
		order.setOrderStatus(2);
		order.setShipStatus(2);
		order.setConfirmTime(new Date());
		if (mallOrderService.updateById(order)) {
			return Rest.ok("收货成功");
		} else {
			return Rest.failure("操作失败");
		}
	}

	private String createBookingCode() {
		Random random = new Random();
		int num = random.nextInt(10000);
		String code = String.format("%04d", num);
		return code;
	}
}

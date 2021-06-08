package com.qingshop.mall.modules.wxapi.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.common.utils.text.Convert;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.service.IMallCartService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/cart")
public class WxCartController extends BaseController {

	@Autowired
	private IMallCartService mallCartService;

	@Autowired
	private IMallGoodsService mallGoodService;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;
	
	@ApiOperation(value = "购物车列表",response = Rest.class)
	@GetMapping("/lists")
	public Rest index(@LoginUser Long userId) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		List<MallCart> cartList = mallCartService.list(new QueryWrapper<MallCart>().eq("user_id", userId));
		Integer goodsCount = 0;
		BigDecimal goodsAmount = new BigDecimal(0.00);
		for (MallCart cart : cartList) {
			goodsCount += cart.getNumber();
			MallGoodsSkudetail mallGoodsSkudetail = mallGoodsSkudetailService.getById(cart.getSkudetailId());
			MallGoods mallGood = mallGoodService.getById(cart.getGoodsId());
			goodsAmount = goodsAmount.add(mallGoodsSkudetail.getGoodPrice().multiply(new BigDecimal(cart.getNumber())));
			cart.setPicUrl(mallGood.getPicUrl());
			cart.setGoodPrice(mallGoodsSkudetail.getGoodPrice());
			cart.setGoodsName(mallGood.getGoodName());
			cart.setSpecifications(mallGoodsSkudetail.getSkudetails());
		}
		Map<String, Object> cartTotal = new HashMap<>();
		cartTotal.put("goodsCount", goodsCount);
		cartTotal.put("goodsAmount", goodsAmount);
		Map<String, Object> result = new HashMap<>();
		result.put("goods_list", cartList);
		result.put("order_total_num", goodsCount);
		result.put("order_total_price", goodsAmount);
		return Rest.okData(result);
	}
	
	@ApiOperation(value = "购物车提交商品",response = Rest.class)
	@PostMapping("/add")
	public Rest addCart(@LoginUser Long userId, @RequestBody MallCart cart) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
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
		// 查询商品是否上下架
		MallGoods mallGood = mallGoodService.getById(goodsId);
		if (mallGood == null || mallGood.getIsShelf() != 1) {
			return Rest.failure("商品已下架");
		}
		// 规格查询
		MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(skudetailId);
		if (skudetail == null) {
			return Rest.failure("商品已下架");
		}
		// 判断购物车中是否存在此规格的商品
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goods_id", goodsId);
		params.put("skudetail_id", skudetailId);
		params.put("user_id", userId);
		MallCart existCart = mallCartService.getOne(new QueryWrapper<MallCart>().allEq(params));
		if (existCart == null) {
			// 取得规格信息，判断规格库存
			if (skudetail == null || number > skudetail.getNumber()) {
				return Rest.failure("库存不足");
			}
			cart.setCartId(DistributedIdWorker.nextId());
			cart.setUserId(Long.valueOf(userId));
			mallCartService.save(cart);
		} else {
			int num = existCart.getNumber() + number;
			if (num > skudetail.getNumber()) {
				return Rest.failure("库存不足");
			}
			existCart.setNumber(num);
			if (!mallCartService.updateById(existCart)) {
				return Rest.failure();
			}
		}
		return Rest.okData(goodscount(userId));
	}

	/**
	 * 购物车商品货品数量 如果用户没有登录，则返回空数据。
	 * 
	 * @param userId
	 *            用户ID
	 * @return 购物车商品货品数量
	 */
	@ApiOperation(value = "购物车商品数量",response = Rest.class)
	@GetMapping("/goodscount")
	public Rest goodscount(@LoginUser Long userId) {
		Map<String, Object> data = new HashMap<>();
		if (userId == null) {
			return Rest.ok();
		}
		int goodsCount = 0;
		List<MallCart> cartList = mallCartService.list(new QueryWrapper<MallCart>().eq("user_id", userId));
		for (MallCart cart : cartList) {
			goodsCount += cart.getNumber();
		}
		data.put("cart_total_num", goodsCount);
		return Rest.okData(data);
	}

	/**
	 * 修改购物车商品货品数量
	 *
	 * @param userId
	 *            用户ID
	 * @param cart
	 *            购物车商品信息， { id: xxx, goodsId: xxx, skudetailId: xxx, number: xxx }
	 * @return 修改结果
	 */
	@ApiOperation(value = "修改购物车商品货品数量",response = Rest.class)
	@PostMapping("/cut")
	public Rest cutCart(@LoginUser Long userId, @RequestBody MallCart cart) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		if (cart == null) {
			return Rest.failure();
		}
		Long skudetailId = cart.getSkudetailId();
		Integer number = cart.getNumber();
		Long goodsId = cart.getGoodsId();
		Long id = cart.getCartId();
		if (!ObjectUtils.allNotNull(id, skudetailId, number, goodsId)) {
			return Rest.failure();
		}
		if (number <= 0) {
			return Rest.failure();
		}

		// 判断是否存在该购物项
		MallCart existCart = mallCartService.getById(id);
		if (existCart == null) {
			return Rest.failure();
		}

		// 判断goodsId和skudetailId是否与当前cart里的值一致
		if (!existCart.getGoodsId().equals(goodsId)) {
			return Rest.failure();
		}

		if (!existCart.getSkudetailId().equals(skudetailId)) {
			return Rest.failure();
		}

		// 判断商品是否可以购买
		MallGoods goods = mallGoodService.getById(goodsId);
		if (goods == null || goods.getIsShelf() != 1) {
			return Rest.failure("商品已下架");
		}

		MallGoodsSkudetail skudetail = mallGoodsSkudetailService.getById(skudetailId);
		if (skudetail == null || skudetail.getNumber() < number) {
			return Rest.failure("库存不足");
		}
		existCart.setNumber(number);
		if (!mallCartService.updateById(existCart)) {
			return Rest.failure();
		}
		return Rest.ok();
	}

	/**
	 * 购物车商品删除
	 *
	 * @param userId
	 *            用户ID
	 * @param cartIds
	 * @return 购物车信息
	 */
	@ApiOperation(value = "购物车商品删除",response = Rest.class)
	@PostMapping("/delete")
	public Rest delete(@LoginUser Long userId, @RequestBody String body) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		JSONObject json = JSONObject.parseObject(body);
		String cartIdList = json.getString("cartIdList");
		if (cartIdList == null || "".equals(cartIdList)) {
			return Rest.failure("移除失败");
		}
		mallCartService.removeByIds(Arrays.asList(Convert.toStrArray(cartIdList)));
		return Rest.ok();
	}

}

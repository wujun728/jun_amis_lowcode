package com.qingshop.mall.modules.wxapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallComment;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSku;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCommentService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkuService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;
import com.qingshop.mall.modules.mall.service.IMallUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController extends BaseController {

	@Autowired
	private IMallGoodsService mallGoodService;

	@Autowired
	private IMallGoodsSkuService mallGoodsSkuService;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;

	@Autowired
	private IMallCategoryService mallCategoryService;

	@Autowired
	private IMallCommentService mallCommentService;

	@Autowired
	private IMallUserService mallUserService;

	/**
	 * 商品列表
	 *
	 * @return 商品列表
	 */
	@ApiOperation(value = "商品列表", response = Rest.class)
	@RequestMapping("/list")
	public Rest list(Integer page, String sortType, Integer sortPrice, Long category_id, String search) {
		try {
			Map<String, Object> data = new HashMap<>();
			Map<String, Object> result = new HashMap<>();
			Page<MallGoods> pages = this.getPage(page, 10);
			QueryWrapper<MallGoods> ew = new QueryWrapper<MallGoods>();
			if (StringUtils.isNotBlank(search)) {
				ew.like("good_name", search);
			}
			if (category_id != null) {
				ew.eq("category_id", category_id);
			}
			if (sortPrice != null && sortPrice == 1) {
				ew.orderByDesc("good_price");
			}
			if (sortPrice != null && sortPrice == 0) {
				ew.orderByAsc("good_price");
			}
			if (sortPrice == null) {
				ew.orderByDesc("create_time");
			}
			IPage<MallGoods> pageData = mallGoodService.page(pages, ew);
			List<MallGoods> proList = pageData.getRecords();
			for (MallGoods mallGood : proList) {
				mallGood.setPicUrl(mallGood.getPicUrl());
			}
			long total = pageData.getTotal();
			result.put("total", total);
			result.put("per_page", 10);
			result.put("current_page", page);
			result.put("last_page", total / 10 + 1);
			result.put("data", proList);
			data.put("list", result);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("商品列表数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	/**
	 * 商品详情
	 * 
	 * @return 详情数据
	 */
	@ApiOperation(value = "商品详情", response = Rest.class)
	@RequestMapping("/detail")
	public Rest detail(Long goods_id) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			MallGoods mallGood = mallGoodService.getById(goods_id);
			List<String> pictureList = (List<String>) JsonUtils.parseArray(mallGood.getGallery(), String.class);
			List<MallGoodsSku> skuList = mallGoodsSkuService.list(new QueryWrapper<MallGoodsSku>().eq("goods_id", goods_id));
			// 数据分组
			Map<String, List<MallGoodsSku>> skuMap = new HashMap<String, List<MallGoodsSku>>();
			for (MallGoodsSku mallGoodsSku : skuList) {
				if (skuMap.containsKey(mallGoodsSku.getSkukey())) {
					skuMap.get(mallGoodsSku.getSkukey()).add(mallGoodsSku);
				} else {
					List<MallGoodsSku> tmpList = new ArrayList<MallGoodsSku>();
					tmpList.add(mallGoodsSku);
					skuMap.put(mallGoodsSku.getSkukey(), tmpList);
				}
			}
			List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
			for (Map.Entry<String, List<MallGoodsSku>> entry : skuMap.entrySet()) {
				Map<String, Object> groupMap = new HashMap<String, Object>();
				groupMap.put("spec_items", entry.getValue());
				groupMap.put("group_name", entry.getKey());
				groupList.add(groupMap);
			}
			Map<String, Object> spec_attr = new HashMap<String, Object>();
			spec_attr.put("spec_attr", groupList);
			List<MallGoodsSkudetail> skuDetailList = mallGoodsSkudetailService.list(new QueryWrapper<MallGoodsSkudetail>().eq("goods_id", goods_id));
			// 商品评价
			Page<MallComment> pages = this.getPage(1, 2);
			QueryWrapper<MallComment> ew = new QueryWrapper<MallComment>();
			ew.eq("value_id", goods_id);
			ew.eq("is_show", 1);
			ew.orderByDesc("create_time");
			IPage<MallComment> pageData = mallCommentService.page(pages, ew);
			List<MallComment> goodsCommentList = pageData.getRecords();
			List<Long> userIds = goodsCommentList.stream().map(m -> m.getUserId()).collect(Collectors.toList());
			Map<Long, Object> userMap = new HashMap<>();
			if (!StringUtils.isEmpty(userIds)) {
				List<MallUser> userList = (List<MallUser>) mallUserService.listByIds(userIds);
				userMap = userList.stream().collect(Collectors.toMap(MallUser::getUserId, a -> a.getNickname(), (k1, k2) -> k1));
			}
			List<Map<String, Object>> goodsComment = new ArrayList<>();
			for (MallComment mallComment : goodsCommentList) {
				Map<String, Object> comment = new HashMap<>();
				comment.put("name", userMap.get(mallComment.getUserId()));
				comment.put("comment_rank", mallComment.getStar());
				comment.put("cont", mallComment.getContent());
				comment.put("time", mallComment.getCreateTime());
				goodsComment.add(comment);
			}
			data.put("pictureList", pictureList);
			data.put("specData", spec_attr);
			data.put("skuDetailList", skuDetailList);
			data.put("mallGood", mallGood);
			data.put("goodsComment", goodsComment);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("商品详情数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	/**
	 * 分类
	 * 
	 * @return 分类数据
	 */
	@ApiOperation(value = "分类数据", response = Rest.class)
	@RequestMapping("/categorylist")
	public Rest getlist(Integer page) {
		try {
			Map<String, Object> data = new HashMap<>();
			List<MallCategory> categoryList = mallCategoryService.list(new QueryWrapper<MallCategory>().eq("parent_id", 0L));
			List<Object> list = new ArrayList<>();
			for (MallCategory mallCategory : categoryList) {
				JSONObject treeObject = new JSONObject(true);
				treeObject.put("category_id", mallCategory.getCategoryId());
				treeObject.put("name", mallCategory.getName());
				treeObject.put("file_path", mallCategory.getPicUrl());
				treeObject.put("child", getChildren(mallCategory.getCategoryId()));
				list.add(treeObject);
			}
			data.put("categorylist", list);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("分类数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	public List<Object> getChildren(Long parentId) {
		List<Object> list = new ArrayList<>();
		List<MallCategory> children = mallCategoryService.list(new QueryWrapper<MallCategory>().eq("parent_id", parentId));
		;
		for (MallCategory mallCategory : children) {
			JSONObject obj = new JSONObject(true);
			obj.put("category_id", mallCategory.getCategoryId());
			obj.put("name", mallCategory.getName());
			obj.put("file_path", mallCategory.getPicUrl());
			obj.put("child", getChildren(mallCategory.getCategoryId()));
			list.add(obj);
		}
		return list;
	}
}

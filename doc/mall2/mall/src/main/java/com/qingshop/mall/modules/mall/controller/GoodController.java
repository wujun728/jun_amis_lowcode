package com.qingshop.mall.modules.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallGoodsSku;
import com.qingshop.mall.modules.mall.entity.MallGoodsSkudetail;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkuService;
import com.qingshop.mall.modules.mall.service.IMallGoodsSkudetailService;
import com.qingshop.mall.modules.mall.vo.GoodsAllinOneVo;

@Controller
@RequestMapping("/mall/good")
public class GoodController extends BaseController {

	@Autowired
	private IMallGoodsService mallGoodsServic;

	@Autowired
	private IMallCategoryService mallCategoryService;

	@Autowired
	private IMallGoodsSkuService mallGoodsSkuService;

	@Autowired
	private IMallGoodsSkudetailService mallGoodsSkudetailService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listGood")
	@RequestMapping("/list")
	public String list() {
		return "mall/good/list";
	}

	/**
	 * 分页查询日志
	 */
	@RequiresPermissions("listGood")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest list(String search, Integer start, Integer length, String categoryId, String parentId) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<MallGoods> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<MallGoods> ew = new QueryWrapper<MallGoods>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("good_name", search);
		}
		if ("-1".equals(categoryId)) {
			categoryId = "";
		}
		// 二级类别查询
		if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(categoryId)) {
			ew.eq("category_id", categoryId);
		}
		// 一级级类别查询
		if (!StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(categoryId)) {
			List<Long> categoryIds = mallCategoryService.selectCategoryIdByPid(categoryId);
			ew.in("category_id", categoryIds);
		}
		ew.orderByDesc("create_time");
		IPage<MallGoods> pageData = mallGoodsServic.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增商品
	 */
	@RequiresPermissions("addGood")
	@RequestMapping("/add")
	public String add(ModelMap model) {
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
		model.put("categoryMapList", categoryMapList);
		return "mall/good/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addGood")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(@RequestBody GoodsAllinOneVo goodsAllinone) {
		return mallGoodsServic.insertMallGoods(goodsAllinone);
	}

	/**
	 * 编辑类目
	 */
	@RequiresPermissions("editGood")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		MallGoods mallGoods = mallGoodsServic.getById(id);
		// 轮播图片处理
		List<String> pictureList = (List<String>) JsonUtils.parseArray(mallGoods.getGallery(), String.class);
		if (!StringUtils.isEmpty(pictureList)) {
			model.addAttribute("pictureList", pictureList);
		}
		model.addAttribute("good", mallGoods);
		// 类目处理数据分组
		List<MallCategory> mallCategories = mallCategoryService.list(null);
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
		model.addAttribute("categoryMapList", categoryMapList);
		// sku 数据分组
		List<MallGoodsSku> skuList = mallGoodsSkuService.list(new QueryWrapper<MallGoodsSku>().eq("goods_id", id));
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
		List<MallGoodsSkudetail> skuDetailList = mallGoodsSkudetailService.list(new QueryWrapper<MallGoodsSkudetail>().eq("goods_id", id));
		if (mallGoods.getSpecType() == 1) {
			model.addAttribute("specTypeDetail", skuDetailList.get(0));
			model.addAttribute("skuDetailList", new ArrayList<MallGoodsSkudetail>());
			model.addAttribute("skuMap", new HashMap<String, List<MallGoodsSku>>());
		} else {
			model.addAttribute("specTypeDetail", new MallGoodsSkudetail());
			model.addAttribute("skuDetailList", skuDetailList);
			model.addAttribute("skuMap", skuMap);
		}
		return "mall/good/edit";
	}

	/**
	 * 执行修改
	 */
	@RequiresPermissions("editGood")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(@RequestBody GoodsAllinOneVo goodsAllinone) {
		return mallGoodsServic.updateMallGoodById(goodsAllinone);
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("deleteGood")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		return mallGoodsServic.deleteMallGoodById(id);
	}
}

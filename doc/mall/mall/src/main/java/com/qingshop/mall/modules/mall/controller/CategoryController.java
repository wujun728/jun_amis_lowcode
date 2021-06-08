package com.qingshop.mall.modules.mall.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.bean.Ztree;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallCategory;
import com.qingshop.mall.modules.mall.entity.MallCoupon;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.service.IMallCategoryService;
import com.qingshop.mall.modules.mall.service.IMallCouponService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;

@Controller
@RequestMapping("/mall/category")
public class CategoryController extends BaseController {

	@Autowired
	private IMallCategoryService mallCategoryService;

	@Autowired
	private IMallCouponService mallCouponService;

	@Autowired
	private IMallGoodsService mallGoodsService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listCategory")
	@RequestMapping("/list")
	public String list() {
		return "mall/category/list";
	}

	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listCategory")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search) {
		Rest resultMap = new Rest();
		QueryWrapper<MallCategory> ew = new QueryWrapper<MallCategory>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("name", search);
		}
		List<MallCategory> categoryList = mallCategoryService.list(ew);
		List<MallCategory> treeList = mallCategoryService.getChildPerms(categoryList, 0);
		resultMap.put("iTotalDisplayRecords", categoryList.size());
		resultMap.put("iTotalRecords", categoryList.size());
		resultMap.put("aaData", treeList);
		return resultMap;
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("addCategory")
	@RequestMapping("/add")
	public String add(Model model) {
		List<MallCategory> categoryList = mallCategoryService.list(new QueryWrapper<MallCategory>().eq("parent_id", 0));
		model.addAttribute("categoryList", categoryList);
		return "mall/category/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addCategory")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(MallCategory mallCategory) {
		mallCategoryService.insertMallCategory(mallCategory);
		return Rest.ok();
	}

	/**
	 * 编辑
	 */
	@RequiresPermissions("editCategory")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		List<MallCategory> categoryList = mallCategoryService.list(new QueryWrapper<MallCategory>().eq("parent_id", 0));
		MallCategory category = mallCategoryService.getById(id);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		return "mall/category/edit";
	}

	/**
	 * 执行编辑
	 */
	@RequiresPermissions("editCategory")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(MallCategory category) {
		MallCategory oldCategory = mallCategoryService.getById(category.getCategoryId());
		String oldCategoryName = oldCategory.getName();
		if (mallCategoryService.updateById(category)) {
			// 修改分类名称则更新优惠券表中的冗余字段
			if (!oldCategoryName.equals(category.getName())) {
				MallCoupon coupon = new MallCoupon();
				coupon.setRangeRelationName(category.getName());
				mallCouponService.update(coupon, new QueryWrapper<MallCoupon>().eq("range_relation_id", category.getCategoryId()));
			}
			return Rest.ok();
		} else {
			return Rest.failure();
		}
	}

	/**
	 * 加载商品列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Ztree> treeData() {
		List<Ztree> ztrees = mallCategoryService.selectCategoryTree(new MallCategory());
		Ztree topZtree = new Ztree();
		topZtree.setId(-1L);
		topZtree.setpId(0L);
		topZtree.setName("全部商品");
		topZtree.setTitle("全部商品");
		topZtree.setChecked(false);
		topZtree.setOpen(true);
		topZtree.setNocheck(false);
		ztrees.add(0, topZtree);
		return ztrees;
	}

	/**
	 * 根据父节点查询子节点的数量
	 */
	@RequestMapping("/categoryCount")
	@ResponseBody
	public Rest categoryCount(Long pid) {
		QueryWrapper<MallCategory> ew = new QueryWrapper<MallCategory>();
		ew.orderByAsc("sort_order");
		ew.eq("parent_id", pid);
		int memuCount = mallCategoryService.count(ew) + 1;
		return Rest.okData(memuCount);
	}

	/**
	 * 删除类目
	 */
	@RequiresPermissions("deleteCategory")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		QueryWrapper<MallCategory> categoryEw = new QueryWrapper<MallCategory>();
		categoryEw.eq("parent_id", id);
		List<MallCategory> categoryList = mallCategoryService.list(categoryEw);
		if (categoryList.size() > 0) {
			return Rest.failure("该类目存在下级类目，无法删除！");
		}

		QueryWrapper<MallGoods> goodEw = new QueryWrapper<MallGoods>();
		goodEw.eq("category_id", id);
		List<MallGoods> goodList = mallGoodsService.list(goodEw);
		if (goodList.size() > 0) {
			return Rest.failure("该类目存在商品，无法删除！");
		}

		mallCategoryService.removeById(id);
		return Rest.ok();
	}
}

package com.qingshop.mall.modules.mall.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallAd;
import com.qingshop.mall.modules.mall.service.IMallAdService;

/**
 * 广告表 前端控制器
 */
@Controller
@RequestMapping("/mall/adv")
public class AdController extends BaseController {
	
	@Autowired
	private IMallAdService mallAdService;
	
	/**
	 * 列表页
	 */
	@RequiresPermissions("listAd")
	@RequestMapping("/list")
	public String list() {
		return "mall/adv/list";
	}
	
	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listAd")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<MallAd> page = getPage(pageIndex, length);
		QueryWrapper<MallAd> ew = new QueryWrapper<MallAd>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("name", search);
		}
		IPage<MallAd> pageData = mallAdService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}
	
	/**
	 * 新增
	 */
	@RequiresPermissions("addAd")
	@RequestMapping("/add")
	public String add() {
		return "mall/adv/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addAd")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(MallAd mallAd) {
		mallAd.setAdId(DistributedIdWorker.nextId());
		mallAdService.save(mallAd);
		return Rest.ok();
	}

	/**
	 * 编辑
	 */
	@RequiresPermissions("editAd")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		MallAd mallAd = mallAdService.getById(id);
		model.addAttribute("mallAd", mallAd);
		return "mall/adv/edit";
	}

	/**
	 * 执行编辑
	 */
	@RequiresPermissions("editAd")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(MallAd mallAd) {
		mallAdService.updateById(mallAd);
		return Rest.ok();
	}
	
	/**
	 * 删除
	 */
	@RequiresPermissions("deleteAd")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		mallAdService.removeById(id);
		return Rest.ok();
	}
}

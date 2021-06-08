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
import com.qingshop.mall.modules.mall.entity.MallIssue;
import com.qingshop.mall.modules.mall.service.IMallIssueService;

@Controller
@RequestMapping("/mall/issue")
public class IssueController extends BaseController {

	@Autowired
	private IMallIssueService mallIssueService;

	@RequiresPermissions("listIssue")
	@RequestMapping("/list")
	public String list() {
		return "/mall/issue/list";
	}

	/**
	 * 分页查询
	 */
	@RequiresPermissions("listIssue")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<MallIssue> page = getPage(pageIndex, length);
		QueryWrapper<MallIssue> ew = new QueryWrapper<MallIssue>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("question", search);
		}
		IPage<MallIssue> pageData = mallIssueService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("addIssue")
	@RequestMapping("/add")
	public String add() {
		return "mall/issue/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addIssue")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(MallIssue mallIssue) {
		mallIssue.setIssueId(DistributedIdWorker.nextId());
		mallIssueService.save(mallIssue);
		return Rest.ok();
	}

	/**
	 * 编辑
	 */
	@RequiresPermissions("editIssue")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		MallIssue mallIssue = mallIssueService.getById(id);
		model.addAttribute("mallIssue", mallIssue);
		return "mall/issue/edit";
	}

	/**
	 * 执行编辑
	 */
	@RequiresPermissions("editIssue")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(MallIssue mallIssue) {
		mallIssueService.updateById(mallIssue);
		return Rest.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("deleteIssue")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		mallIssueService.removeById(id);
		return Rest.ok();
	}
}

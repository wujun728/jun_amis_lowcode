package com.ifast.business.demo.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ifast.business.demo.domain.DemoDO;
import com.ifast.business.demo.service.DemoService;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.utils.Result;

/**
 * 
 * <pre>
 * 基础表
 * </pre>
 * 
 * <small> 2018-07-27 23:38:24 | Aron</small>
 */
@Controller
@RequestMapping("/demo/demoBase")
public class DemoController extends AdminBaseController {
	@Autowired
	private DemoService demoBaseService;

	@GetMapping()
	@RequiresPermissions("demo:demoBase:demoBase")
	String DemoBase() {
		return "demo/demoBase/demoBase";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("demo:demoBase:demoBase")
	public Result<Page<DemoDO>> list(DemoDO demoBaseDTO) {
		Wrapper<DemoDO> wrapper = new QueryWrapper<DemoDO>()
		    // .orderBy("id", false);
		    .orderByAsc("id")
		    .like("title", demoBaseDTO.getTitle())
		    .like("content", demoBaseDTO.getContent());

		Page<DemoDO> page = (Page<DemoDO>) demoBaseService.page(getPage(DemoDO.class), wrapper);
		return Result.ok(page);
	}

	@GetMapping("/add")
	@RequiresPermissions("demo:demoBase:add")
	String add() {
		return "demo/demoBase/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("demo:demoBase:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		DemoDO demoBase = demoBaseService.getById(id);
		model.addAttribute("demoBase", demoBase);
		return "demo/demoBase/edit";
	}

	/**
	 * 保存
	 */
	@Log("添加基础表数据")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("demo:demoBase:add")
	public Result<String> save(DemoDO demoBase) {
		boolean insert = demoBaseService.save(demoBase);
		return insert ? Result.ok() : Result.fail();
	}

	/**
	 * 修改
	 */
	@Log("更新基础表数据")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("demo:demoBase:edit")
	public Result<String> update(DemoDO demoBase) {
		boolean updateById = demoBaseService.updateById(demoBase);
		return updateById ? Result.ok() : Result.fail();
	}

	/**
	 * 删除
	 */
	@Log("删除基础表数据")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("demo:demoBase:remove")
	public Result<String> remove(Long id) {
		demoBaseService.removeById(id);
		return Result.ok();
	}

	/**
	 * 删除
	 */
	@Log("批量删除基础表数据")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("demo:demoBase:batchRemove")
	public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
		demoBaseService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}

}

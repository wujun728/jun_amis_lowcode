package com.qingshop.mall.modules.system.controller;

import java.util.Arrays;

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
import com.qingshop.mall.framework.annotation.Log;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysDept;
import com.qingshop.mall.modules.system.service.ISysDeptService;

/**
 * 部门控制器
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

	@Autowired
	private ISysDeptService sysDeptService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listDept")
	@RequestMapping("/list")
	public String list() {
		return "system/dept/list";
	}

	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listDept")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysDept> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysDept> ew = new QueryWrapper<SysDept>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("deptName", search);
		}
		IPage<SysDept> pageData = sysDeptService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增部门
	 */
	@RequiresPermissions("addDept")
	@RequestMapping("/add")
	public String add() {
		return "system/dept/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addDept")
	@Log("创建部门")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysDept dept) {
		dept.setDeptId(DistributedIdWorker.nextId());
		sysDeptService.save(dept);
		return Rest.ok();
	}

	@RequiresPermissions("deleteDept")
	@Log("删除部门")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(String id) {
		String[] idArry = id.split(",");
		sysDeptService.removeByIds(Arrays.asList(idArry));
		return Rest.ok();
	}

	/**
	 * 编辑部门
	 */
	@RequiresPermissions("editDept")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		SysDept dept = sysDeptService.getById(id);
		model.addAttribute("dept", dept);
		return "system/dept/edit";
	}

	/**
	 * 执行编辑
	 */
	@RequiresPermissions("editDept")
	@Log("编辑部门")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysDept dept) {
		sysDeptService.updateById(dept);
		return Rest.ok();
	}

}

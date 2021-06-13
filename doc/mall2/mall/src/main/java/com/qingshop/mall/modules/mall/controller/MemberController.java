package com.qingshop.mall.modules.mall.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallUserService;

@Controller
@RequestMapping("/mall/user")
public class MemberController extends BaseController {

	@Autowired
	private IMallUserService mallUserService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listVip")
	@RequestMapping("/list")
	public String list() {
		return "mall/user/list";
	}

	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listVip")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search) {
		Rest resultMap = new Rest();
		QueryWrapper<MallUser> ew = new QueryWrapper<MallUser>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("username", search);
		}
		List<MallUser> categoryList = mallUserService.list(ew);
		resultMap.put("iTotalDisplayRecords", categoryList.size());
		resultMap.put("iTotalRecords", categoryList.size());
		resultMap.put("aaData", categoryList);
		return resultMap;
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("deleteVip")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		mallUserService.removeById(id);
		return Rest.ok();
	}
}

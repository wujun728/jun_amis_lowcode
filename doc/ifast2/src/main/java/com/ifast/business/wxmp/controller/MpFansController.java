package com.ifast.business.wxmp.controller;

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
import com.ifast.business.wxmp.domain.MpFansDO;
import com.ifast.business.wxmp.service.MpFansService;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.utils.Result;

/**
 * <pre>
 * 微信粉丝表
 * </pre>
 * 
 * <small> 2018-04-11 23:27:06 | Aron</small>
 */
@Controller
@RequestMapping("/wxmp/mpFans")
public class MpFansController extends AdminBaseController {
	@Autowired
	private MpFansService mpFansService;

	@GetMapping()
	@RequiresPermissions("wxmp:mpFans:mpFans")
	String MpFans() {
		return "wxmp/mpFans/mpFans";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wxmp:mpFans:mpFans")
	public Result<Page<MpFansDO>> list(MpFansDO mpFansDTO) {
		Wrapper<MpFansDO> wrapper = new QueryWrapper<MpFansDO>(mpFansDTO);
		// Page<MpFansDO> page = mpFansService.selectPage(getPage(MpFansDO.class), wrapper);
		Page<MpFansDO> page = (Page<MpFansDO>) mpFansService.page(getPage(MpFansDO.class), wrapper);
		return Result.ok(page);
	}

	@GetMapping("/add")
	@RequiresPermissions("wxmp:mpFans:add")
	String add() {
		return "wxmp/mpFans/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wxmp:mpFans:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		// MpFansDO mpFans = mpFansService.selectById(id);
		MpFansDO mpFans = mpFansService.getById(id);
		model.addAttribute("mpFans", mpFans);
		return "wxmp/mpFans/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wxmp:mpFans:add")
	public Result<String> save(MpFansDO mpFans) {
		// mpFansService.insert(mpFans);
		mpFansService.save(mpFans);
		return Result.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wxmp:mpFans:edit")
	public Result<String> update(MpFansDO mpFans) {
		mpFansService.updateById(mpFans);
		return Result.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpFans:remove")
	public Result<String> remove(Long id) {
		// mpFansService.deleteById(id);
		mpFansService.removeById(id);
		return Result.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpFans:batchRemove")
	public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
		// mpFansService.deleteBatchIds(Arrays.asList(ids));
		mpFansService.removeByIds(Arrays.asList(ids));
		return Result.ok();
	}

}

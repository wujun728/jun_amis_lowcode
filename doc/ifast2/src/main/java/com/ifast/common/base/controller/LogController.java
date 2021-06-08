package com.ifast.common.base.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.base.domain.LogDO;
import com.ifast.common.base.service.LogService;
import com.ifast.common.utils.Result;

/**
 * <pre>
 * 日志
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@RequestMapping("/common/log")
@Controller
public class LogController extends AdminBaseController {
	@Autowired
	LogService logService;
	String     prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	public Result<Page<LogDO>> list(LogDO logDTO) {
		QueryWrapper<LogDO> wrapper = logService.convertToEntityWrapper("username", logDTO.getUsername());
		wrapper.eq(logDTO.getUserId() != null, "userId", logDTO.getUserId());
		wrapper.like("operation", logDTO.getOperation());
		Page<LogDO> page = (Page<LogDO>) logService.page(getPage(LogDO.class), wrapper);
		return Result.ok(page);
	}

	@Log("删除系统日志")
	@ResponseBody
	@PostMapping("/remove")
	Result<String> remove(Long id) {
		logService.removeById(id);
		return Result.ok();
	}

	@Log("批量删除系统日志")
	@ResponseBody
	@PostMapping("/batchRemove")
	Result<String> batchRemove(@RequestParam("ids[]") Long[] ids) {
		logService.removeByIds(Arrays.asList(ids));
		return Result.fail();
	}
}

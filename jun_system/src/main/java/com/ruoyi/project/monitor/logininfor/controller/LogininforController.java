package com.ruoyi.project.monitor.logininfor.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.logininfor.service.LogininforService;

/**
 * 登录日志控制类
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class LogininforController extends BaseController {
	private String prefix = "monitor/logininfor";

	@Autowired
	private LogininforService logininforService;

	@Autowired
	private PasswordService passwordService;

	@RequiresPermissions("monitor:logininfor:view")
	@GetMapping()
	public String logininfor() {
		return prefix + "/logininfor";
	}

	@Log(title = "登录日志", businessType = BusinessType.QUERY)
	@RequiresPermissions("monitor:logininfor:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HttpServletRequest request) {
		return logininforService.selectLogininforList(request, true);
	}

	@Log(title = "登录日志", businessType = BusinessType.EXPORT)
	@RequiresPermissions("monitor:logininfor:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HttpServletRequest request) {

		LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();
		// 定义表头
		headers.put("info_id", "访问编号");
		headers.put("login_name", "登录名称");
		headers.put("ipaddr", "登录地址");
		headers.put("login_location", "登录地点");
		headers.put("browser", "浏览器");
		headers.put("os", "操作系统");
		headers.put("status_name", "状态");
		headers.put("msg", "操作信息");
		headers.put("login_time", "登录时间");

		//数据集合
		List<?> dataList = logininforService.selectLogininforList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "登录日志");
	}

	@RequiresPermissions("monitor:logininfor:remove")
	@Log(title = "登录日志", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(logininforService.deleteLogininforByIds(ids));
	}

	@RequiresPermissions("monitor:logininfor:remove")
	@Log(title = "登录日志", businessType = BusinessType.CLEAN)
	@PostMapping("/clean")
	@ResponseBody
	public AjaxResult clean() {
		logininforService.cleanLogininfor();
		return success();
	}

    @RequiresPermissions("monitor:logininfor:unlock")
    @Log(title = "登录日志", businessType = BusinessType.UNLOCK)
    @PostMapping("/unlock")
    @ResponseBody
	public AjaxResult unlock(String loginName) {
        passwordService.clearLoginRecordCache(loginName);
		return success();
	}
}
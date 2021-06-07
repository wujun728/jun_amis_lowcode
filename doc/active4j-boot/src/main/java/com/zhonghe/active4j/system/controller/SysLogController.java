package com.zhonghe.active4j.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.system.entity.SysLogEntity;
import com.zhonghe.active4j.system.service.SysLogService;
import com.zhonghe.active4j.system.wrapper.LogWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @title SysLogController.java
 * @description 
		  系统日志管理
 * @time  2019年12月3日 上午10:13:38
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/log")
@Slf4j
public class SysLogController extends BaseController {

	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 跳转到列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return "sys/log/log_list.html";
	}
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysUserEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysLogEntity sysLogEntity, PageInfo<SysLogEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysLogEntity> queryWrapper = QueryUtils.installQueryWrapper(sysLogEntity, request.getParameterMap());
		
		//执行查询
		IPage<SysLogEntity> lstResult = sysLogService.page(page.getPageEntity(), queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new LogWrapper(lstResult).wrap());
	}
	
}

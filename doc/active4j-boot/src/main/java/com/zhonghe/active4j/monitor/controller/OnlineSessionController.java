package com.zhonghe.active4j.monitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;
import com.zhonghe.active4j.monitor.service.OnlineSessionService;
import com.zhonghe.active4j.monitor.wrapper.OnlineSessionWrapper;
import com.zhonghe.active4j.system.controller.SysUserController;

import lombok.extern.slf4j.Slf4j;

/**
 * @title OnlineUserController.java
 * @description 
		  在线用户显示列表
 * @time  2019年12月9日 上午11:10:24
 * @author 麻木神
 * @version 1.0
*/
@Controller
@RequestMapping("/monitor/online")
@Slf4j
public class OnlineSessionController {
	
	@Autowired
	private OnlineSessionService onlineSessionService;

	
	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "monitor/online/";

	
	@RequestMapping("/index")
	@Log(type = LogType.normal, name = "现在用户列表", memo = "现在用户列表")
	public String index(Model model) {
		
		return prefix_page + "index.html";
	}
	
	
	/**
	 *  获取表格数据 树形结构
	 * @param sysUserEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(OnlineSessionEntity onlineSessionEntity, PageInfo<OnlineSessionEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<OnlineSessionEntity> queryWrapper = QueryUtils.installQueryWrapper(onlineSessionEntity, request.getParameterMap());
		
		//执行查询
		IPage<OnlineSessionEntity> lstResult = onlineSessionService.page(page.getPageEntity(), queryWrapper);

		//结果处理,直接写到客户端
		ResponseUtil.write(response, new OnlineSessionWrapper(lstResult).wrap());
	}
}

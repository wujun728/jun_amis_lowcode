package com.zhonghe.active4j.func.timer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.func.timer.entity.QuartzJobLogEntity;
import com.zhonghe.active4j.func.timer.service.QuartzJobLogService;
import com.zhonghe.active4j.func.timer.wrapper.QuartzJobLogWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title QuartzJobLogController.java
 * @description 
		定时任务日志管理
 * @time  2019年12月12日 上午10:52:50
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/timer/joblog")
@Slf4j
public class QuartzJobLogController extends BaseController {
	
	@Autowired
	private QuartzJobLogService quartzJobLogService;

	private static final String prefix_page = "func/timer/joblog/";
	
	/**
	 * 
	 * @description
	 *  	跳转列表页
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月10日 上午10:15:00
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return prefix_page + "joblog_list.html";
	}
	
	/**
	 * 
	 * @description
	 *  	获取表格数据
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月10日 上午10:20:42
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(QuartzJobLogEntity quartzJobLogEntity, PageInfo<QuartzJobLogEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<QuartzJobLogEntity> queryWrapper = QueryUtils.installQueryWrapper(quartzJobLogEntity, request.getParameterMap());
		//执行查询
		IPage<QuartzJobLogEntity> lstResult = quartzJobLogService.page(page.getPageEntity(), queryWrapper);
		//结果处理，直接写到客户端
		ResponseUtil.write(response, new QuartzJobLogWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到明细页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月11日 下午4:56:55
	 */
	@RequestMapping("/detail")
	public String detail(String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			//获取任务明细
			model = quartzJobLogService.getLogDetailModel(id, model);
		}
		return prefix_page + "detail.html";
	}
	
	/**
	 * 
	 * @description
	 *  	删除定时任务日志
	 * @params
	 *      ids 任务日志ids
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午4:20:08
	 */
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除定时任务日志", memo = "删除了定时任务日志")
	public AjaxJson del(String ids) {
		AjaxJson j = new AjaxJson();
		try {
			//ids校验
			if(StringUtils.isEmpty(ids)) {
				j.setSuccess(false);
				j.setMsg("请选择一条记录");
			}
			//删除定时任务日志
			quartzJobLogService.delLogs(ids);
		}catch(Exception e) {
			log.error("删除定时任务日志报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	清空定时任务日志
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月12日 上午10:57:33
	 */
	@RequestMapping("/empty")
	@ResponseBody
	@Log(type = LogType.del, name = "清空定时任务日志", memo = "清空了所有定时任务日志")
	public AjaxJson empty() {
		AjaxJson j = new AjaxJson();
		try {
			//清空定时任务日志
			quartzJobLogService.remove(new QueryWrapper<QuartzJobLogEntity>());
		}catch(Exception e) {
			log.error("清空定时任务日志报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
}

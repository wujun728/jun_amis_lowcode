package com.zhonghe.active4j.func.timer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
import com.zhonghe.active4j.func.timer.entity.QuartzJobEntity;
import com.zhonghe.active4j.func.timer.service.QuartzJobService;
import com.zhonghe.active4j.func.timer.util.CronUtils;
import com.zhonghe.active4j.func.timer.wrapper.QuartzJobWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title QuartzJobController.java
 * @description 
		定时任务管理
 * @time  2019年12月10日 上午10:10:26
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/timer/job")
@Slf4j
public class QuartzJobController extends BaseController {
	
	@Autowired
	private QuartzJobService quartzJobService;

	private static final String prefix_page = "func/timer/job/";
	
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
		return prefix_page + "job_list.html";
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
	public void datagrid(QuartzJobEntity quartzJobEntity, PageInfo<QuartzJobEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<QuartzJobEntity> queryWrapper = QueryUtils.installQueryWrapper(quartzJobEntity, request.getParameterMap());
		//执行查询
		IPage<QuartzJobEntity> lstResult = quartzJobService.page(page.getPageEntity(), queryWrapper);
		//结果处理，直接写到客户端
		ResponseUtil.write(response, new QuartzJobWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到新增页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月11日 下午2:42:21
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return prefix_page + "add.html";
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
			model = quartzJobService.getJobDetailModel(id, model);
		}
		return prefix_page + "detail.html";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到编辑页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月11日 下午5:08:20
	 */
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			//获取任务实体
			QuartzJobEntity job = quartzJobService.getById(id);
			model.addAttribute("job", job);
		}
		return prefix_page + "edit.html";
	}
	
	/**
	 * 
	 * @description
	 *  	保存定时任务
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午3:27:40
	 */
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存定时任务", memo = "新增或编辑保存了定时任务")
	public AjaxJson save(@Validated QuartzJobEntity quartzJobEntity)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//这里使用了@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理，所以不再做字段为空和长度校验，具体校验规则请见是实体字段
			//校验cron表达式
			if(!CronUtils.isValid(quartzJobEntity.getCronExpression())) {
				j.setSuccess(false);
				j.setMsg("请输入正确的cron表达式");
				return j;
			}
			
			//保存定时任务
			j = quartzJobService.saveJob(quartzJobEntity);
		}catch(Exception e) {
			log.error("保存定时任务报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	删除定时任务
	 * @params
	 *      ids 任务ids
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午4:20:08
	 */
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除定时任务", memo = "删除了定时任务")
	public AjaxJson del(String ids) {
		AjaxJson j = new AjaxJson();
		try {
			//ids校验
			if(StringUtils.isEmpty(ids)) {
				j.setSuccess(false);
				j.setMsg("请选择一条记录");
			}
			//删除定时任务
			quartzJobService.delJobs(ids);
		}catch(Exception e) {
			log.error("删除定时任务报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	启用定时任务
	 * @params
	 *      id 任务id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午1:49:44
	 */
	@RequestMapping("/start")
	@ResponseBody
	@Log(type = LogType.update, name = "启用定时任务", memo = "启用了定时任务")
	public AjaxJson start(String id) {
		AjaxJson j = new AjaxJson();
		try {
			//id校验
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("该任务不存在，请重新选择");
				return j;
			}
			//启用任务
			j = quartzJobService.startJob(id);
		}catch(Exception e) {
			log.error("启用定时任务报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	立即执行一次定时任务
	 * @params
	 *      id 任务id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月12日 上午12:51:33
	 */
	@RequestMapping("/one")
	@ResponseBody
	@Log(type = LogType.save, name = "执行定时任务", memo = "立即执行了一次定时任务")
	public AjaxJson one(String id) {
		AjaxJson j = new AjaxJson();
		try {
			//id校验
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("该任务不存在，请重新选择");
				return j;
			}
			//启用任务
			if(!quartzJobService.runAJobNow(id)) {
				j.setSuccess(false);
				j.setMsg("立即执行一次任务失败");
				return j;
			}
		}catch(Exception e) {
			log.error("立即执行一次定时任务报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	暂停定时任务
	 * @params
	 *      id 任务id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午1:50:38
	 */
	@RequestMapping("/pause")
	@ResponseBody
	@Log(type = LogType.update, name = "暂停定时任务", memo = "暂停了定时任务")
	public AjaxJson pause(String id) {
		AjaxJson j = new AjaxJson();
		try {
			//id校验
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("该任务不存在，请重新选择");
				return j;
			}
			//暂停任务
			j = quartzJobService.pauseJob(id);
		}catch(Exception e) {
			log.error("暂停定时任务报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			e.printStackTrace();
		}
		
		return j;
	}
	
}

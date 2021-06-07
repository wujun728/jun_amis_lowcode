package com.zhonghe.active4j.func.message.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.func.message.entity.SysUserMessageEntity;
import com.zhonghe.active4j.func.message.service.SysUserMessageService;
import com.zhonghe.active4j.func.message.wrapper.SysUserMessageWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title SysUserMessageController.java
 * @description 
		用户系统消息
 * @time  2019年12月19日 上午11:36:55
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/message/user")
@Slf4j
public class SysUserMessageController extends BaseController {
	
	@Autowired
	private SysUserMessageService sysUserMessageService;
	
	private static final String prefix_page = "func/message/user/";
	
	/**
	 * 
	 * @description
	 *  	跳转用户消息主页
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月19日 上午11:37:11
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return prefix_page + "index.html";
	}
	
	/**
	 * 
	 * @description
	 *  	主页获取系统未读消息，显示原点
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 上午11:37:30
	 */
	@RequestMapping("/newmsg")
	@ResponseBody
	public AjaxJson newMsg(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try{
			//获取登录用户id
			String userId = ShiroUtils.getSessionUserId();
			//拼装查询条件
			QueryWrapper<SysUserMessageEntity> queryWrapper = new QueryWrapper<SysUserMessageEntity>();
			queryWrapper.eq("USER_ID", userId);
			queryWrapper.eq("DELETE_STATUS", SysUserMessageEntity.Sys_User_Message_Delete_No);
			queryWrapper.eq("READ_STATUS", SysUserMessageEntity.Sys_User_Message_Read_No);
			List<SysUserMessageEntity> lstMsgs = sysUserMessageService.list(queryWrapper);
			if(null == lstMsgs || lstMsgs.size() == 0) {
				j.setSuccess(false);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			log.error("获取系统消息显示原点报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	显示表格数据
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月19日 下午1:24:09
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysUserMessageEntity sysUserMessageEntity, PageInfo<SysUserMessageEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//获取登录用户id
		String userId = ShiroUtils.getSessionUserId();
		sysUserMessageEntity.setUserId(userId);
		//获取未删除的信息
		sysUserMessageEntity.setDeleteStatus(SysUserMessageEntity.Sys_User_Message_Delete_No);
		//拼接查询条件
		QueryWrapper<SysUserMessageEntity> queryWrapper = QueryUtils.installQueryWrapper(sysUserMessageEntity, request.getParameterMap());
		//执行查询
		IPage<SysUserMessageEntity> lstResult = sysUserMessageService.page(page.getPageEntity(), queryWrapper);
		//结果处理，直接写到客户端
		ResponseUtil.write(response, new SysUserMessageWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	跳转消息明细页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月19日 下午1:44:38
	 */
	@RequestMapping("/detail")
	public String detail(String id, Model model) {
		//根据id获取任务实体
		SysUserMessageEntity msg = sysUserMessageService.getById(id);
		if(null != msg) {
			//消息设为已读
			msg.setReadStatus(SysUserMessageEntity.Sys_User_Message_Read_Yes);
			//更新保存
			sysUserMessageService.saveOrUpdate(msg);
			model.addAttribute("publicTime", DateUtils.getDate2Str(msg.getPublicTime()));
			model.addAttribute("msg", msg);
		}
		
		return prefix_page + "detail.html";
	}
	
	/**
	 * 
	 * @description
	 *  	删除用户系统消息
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 下午1:48:08
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除用户系统消息", memo = "删除了用户系统消息")
	public AjaxJson delete(String ids, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try {
			if(StringUtils.isNotEmpty(ids)) {
				//批量删除用户消息
				sysUserMessageService.deleteMsg(ids);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
			log.error("删除用户系统消息报错，错误信息：{}", e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	已读用户系统消息
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 下午1:53:32
	 */
	@RequestMapping("/read")
	@ResponseBody
	public AjaxJson read(String ids, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try {
			if(StringUtils.isNotEmpty(ids)) {
				//已读用户消息
				sysUserMessageService.readMsg(ids);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("已读用户系统消息失败");
			log.error("已读用户系统消息报错，错误信息：{}", e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	全部已读用户系统消息
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 下午1:59:05
	 */
	@RequestMapping("/readall")
	@ResponseBody
	public AjaxJson readAll(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try {
			//获取登录的userId
			String userId = ShiroUtils.getSessionUserId();
			//已读全部用户消息
			sysUserMessageService.readAllMsg(userId);
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("全部已读用户系统消息失败");
			log.error("全部已读用户系统消息报错，错误信息：{}", e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
}

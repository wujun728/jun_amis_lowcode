package com.zhonghe.active4j.func.message.controller;

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
import com.zhonghe.active4j.core.util.MyBeanUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.func.message.entity.SysMessageEntity;
import com.zhonghe.active4j.func.message.service.SysMessageService;
import com.zhonghe.active4j.func.message.wrapper.SysMessageWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title SysMessageController.java
 * @description 
		系统消息
 * @time  2019年12月18日 下午3:44:36
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/message/sys")
@Slf4j
public class SysMessageController extends BaseController {
	
	@Autowired
	private SysMessageService sysMessageService;
	
	private static final String prefix_page = "func/message/sys/";
	
	/**
	 * 
	 * @description
	 *  	跳转导入导出页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月18日 下午4:12:11
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		return prefix_page + "message_list.html";
	}
	
	/**
	 * 
	 * @description
	 *  	获取表格数据
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月18日 下午4:05:52
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(SysMessageEntity sysMessageEntity, PageInfo<SysMessageEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<SysMessageEntity> queryWrapper = QueryUtils.installQueryWrapper(sysMessageEntity, request.getParameterMap());
		//执行查询
		IPage<SysMessageEntity> lstResult = sysMessageService.page(page.getPageEntity(), queryWrapper);
		//结果处理，直接写到客户端
		ResponseUtil.write(response, new SysMessageWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到新增页面
	 * @params
	 *      id 系统消息id
	 * @return String
	 * @author guyp
	 * @time 2019年12月18日 下午4:36:16
	 */
	@RequestMapping("/add")
	public String add(String id, Model model) {
		//根据id获取任务实体
		SysMessageEntity msg = sysMessageService.getById(id);
		if(null != msg) {
			model.addAttribute("msg", msg);
		}
		return prefix_page + "add.html";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转明细页面
	 * @params
	 *      id 系统消息id
	 * @return String
	 * @author guyp
	 * @time 2019年12月18日 下午4:41:54
	 */
	@RequestMapping("/detail")
	public String detail(String id, Model model) {
		if(StringUtils.isNotEmpty(id)) {
			//根据id获取系统消息实体返回
			model = sysMessageService.getMsgDetailModel(id, model);
		}
		return prefix_page + "detail.html";
	}
	
	/**
	 * 
	 * @description
	 *  	保存系统消息
	 * @params
	 *      sysMessageEntity 系统消息实体
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月18日 下午5:28:02
	 */
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存系统消息", memo = "新增或编辑保存了系统消息")
	public AjaxJson save(@Validated SysMessageEntity sysMessageEntity) {
		AjaxJson j = new AjaxJson();
		
		try{
			//新增保存
			if(StringUtils.isEmpty(sysMessageEntity.getId())) {
				//系统消息状态设为草稿
				sysMessageEntity.setStatus(SysMessageEntity.Sys_Message_Status_Draft);
				//保存
				sysMessageService.save(sysMessageEntity);
			}
			//编辑保存
			else {
				//根据id获取实体
				SysMessageEntity temp = sysMessageService.getById(sysMessageEntity.getId());
				//拷贝实体
				MyBeanUtils.copyBeanNotNull2Bean(sysMessageEntity, temp);
				//保存
				sysMessageService.saveOrUpdate(temp);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("保存失败");
			log.error("保存系统消息报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	删除系统消息
	 * @params
	 *      id 系统消息id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月18日 下午5:29:40
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Log(type = LogType.del, name = "删除系统消息", memo = "删除了系统消息")
	public AjaxJson delete(String id) {
		AjaxJson j = new AjaxJson();
		
		try {
			//id不为空就删除消息实体
			if(StringUtils.isNotEmpty(id)) {
				sysMessageService.removeById(id);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
			log.error("删除系统消息报错，错误信息：{}", e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	发布系统消息
	 * @params
	 *      id 消息实体id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 上午9:30:18
	 */
	@RequestMapping("/publish")
	@ResponseBody
	@Log(type = LogType.normal, name = "发布系统消息", memo = "发布了系统消息")
	public AjaxJson publish(String id) {
		AjaxJson j = new AjaxJson();
		
		try {
			if(StringUtils.isNotEmpty(id)) {
				//根据id获取消息实体
				SysMessageEntity msg = sysMessageService.getById(id);
				//判断是否已发布
				if(StringUtils.equals(SysMessageEntity.Sys_Message_Status_Publish, msg.getStatus())) {
					j.setSuccess(false);
					j.setMsg("已经发布的消息，不能再次发布");
					return j;
				}
				//线程池发送系统消息
				sysMessageService.publishSysMsg(msg);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("发布失败");
			log.error("发布系统消息报错，错误信息：{}", e.getMessage() );
			e.printStackTrace();
		}
		
		return j;
	}
	
}

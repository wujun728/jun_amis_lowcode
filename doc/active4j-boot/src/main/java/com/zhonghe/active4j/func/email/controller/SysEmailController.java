package com.zhonghe.active4j.func.email.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.func.email.service.SysEmailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title SysEmailController.java
 * @description 
		  系统默认邮件发送，邮箱参数配置
 * @time  2019年12月3日 上午10:13:13
 * @author chenxl
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/email")
@Slf4j
public class SysEmailController extends BaseController {

	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "sys/email/";
	
	@Autowired
	private SysEmailService sysEmailService;
	
	/**
	 * 
	 * @description
	 *  	跳转到邮件发送页面
	 * @params
	 *      model
	 * @return String
	 * @author mhm
	 * @time 2019年12月9日 下午3:52:37
	 */
	@RequestMapping("/form")
	public String form(Model model) {
		
		return prefix_page + "email_form.html";
		
	}
	
	/**
	 * 
	 * @description
	 *  	邮件发送
	 * @params
	 *      mailbox
	 *      subject
	 *      content
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月9日 下午3:51:29
	 */
	@RequestMapping("/send")
	@ResponseBody
	public AjaxJson send(String mailbox, String subject, String content)  {
		AjaxJson j = new AjaxJson();
		
		try{
			//后端校验
			if(StringUtils.isEmpty(mailbox)) {
				j.setSuccess(false);
				j.setMsg("收件人邮箱不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(subject)) {
				j.setSuccess(false);
				j.setMsg("主题不能为空");
				return j;
			}
			
			if(StringUtils.isEmpty(content)) {
				j.setSuccess(false);
				j.setMsg("内容不能为空");
				return j;
			}
			
			//邮件发送，html类型（其余方法见sysEmailService）
			sysEmailService.sendHtmlMail(mailbox, subject, content);
		}catch(Exception e) {
			log.error("邮件发送报错，错误信息:" + e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	
}

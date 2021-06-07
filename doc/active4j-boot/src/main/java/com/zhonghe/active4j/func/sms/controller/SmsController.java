package com.zhonghe.active4j.func.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.func.sms.properties.AliSmsProperties;
import com.zhonghe.active4j.func.sms.properties.QcloudSmsProperties;
import com.zhonghe.active4j.func.sms.properties.YunChaoYunProperties;
import com.zhonghe.active4j.func.sms.util.SmsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @title SmsController.java
 * @description 
		  集成短信功能  腾讯云  阿里云  云潮云
 * @time  2019年12月10日 下午2:21:36
 * @author 麻木神
 * @version 1.0
*/
@Controller
@RequestMapping("/func/sms")
@Slf4j
public class SmsController extends BaseController {
	
	@Autowired
	private YunChaoYunProperties yunchaoyunProperties;
	
	@Autowired
	private QcloudSmsProperties qcloudSmsProperties;
	
	@Autowired
	private AliSmsProperties aliSmsProperties;
	
	
	
	public static final String prefix_url = "func/sms/";

	/**
	 * @description
	 *  	使用云潮云
	 * @return String
	 * @author 麻木神
	 * @time 2019年12月10日 下午2:22:30
	 */
	@RequestMapping("/yunchaoyun")
	public String yunchaoyun(Model model) {
		
		/**
		 * 只是为了演示方便这么写
		 */
		String apiKey = yunchaoyunProperties.getApikey();
		
		String apiToken = yunchaoyunProperties.getApiToken();
		
		String smsSign = "active4j开发平台";
		
		String templateNo = "1912100047";
		
		model.addAttribute("apiKey", apiKey);
		model.addAttribute("apiToken", apiToken);
		model.addAttribute("smsSign", smsSign);
		model.addAttribute("templateNo", templateNo);
		
		return prefix_url + "yunchaoyun.html";
	} 
	
	
	/**
	 * @description
	 *  	演示利用云潮云短信SDK发送短信
	 * @return AjaxJson
	 * @author 麻木神
	 * @time 2019年12月10日 下午3:57:28
	 */
	@RequestMapping("/sendYunchaoyunSms")
	@ResponseBody
	public AjaxJson sendYunchaoyunSms(String apiKey, String apiToken, String smsSign, String templateNo, String phoneNo) {
		AjaxJson j = new AjaxJson();
		try {
			
			SmsUtil.sendYunChaoYunSms(apiKey, apiToken, smsSign, templateNo, phoneNo, new String[] {"8888"});
			j.setMsg("短信提交成功, 是否能接受到短信，还需要运营商返回状态");
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("短信发送失败");
			log.error(e.getMessage(), e);
		}
		
		return j;
	}
	
	
	
	
	/**
	 * @description
	 *  	使用腾讯云
	 * @return String
	 * @author 麻木神
	 * @time 2019年12月10日 下午2:22:30
	 */
	@RequestMapping("/qcloudsms")
	public String qcloudsms(Model model) {
		
		/**
		 * 只是为了演示方便这么写
		 */
		String appid = qcloudSmsProperties.getAppid();
		
		String appkey = qcloudSmsProperties.getAppkey();
		
		String smsSign = "江苏众禾";
		
		String templateId = "494564";
		
		model.addAttribute("appid", appid);
		model.addAttribute("appkey", appkey);
		model.addAttribute("smsSign", smsSign);
		model.addAttribute("templateId", templateId);
		
		return prefix_url + "qcloudsms.html";
	} 
	
	
	/**
	 * @description
	 *  	演示利用腾讯云短信SDK发送短信
	 * @return AjaxJson
	 * @author 麻木神
	 * @time 2019年12月10日 下午3:57:28
	 */
	@RequestMapping("/sendQcloudSms")
	@ResponseBody
	public AjaxJson sendQcloudSms(String appid, String appkey, String smsSign, String templateId, String phoneNo) {
		AjaxJson j = new AjaxJson();
		try {
			
			SmsUtil.sendQcloudSms(appid, appkey, smsSign, templateId, phoneNo, new String[] {"8888"});
			j.setMsg("短信提交成功, 是否能接受到短信，还需要运营商返回状态");
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("短信发送失败");
			log.error(e.getMessage(), e);
		}
		
		return j;
	}
	
	
	/**
	 * 
	 * @description
	 *  	跳转到阿里云短信页面
	 * @return String
	 * @author 麻木神
	 * @time 2019年12月16日 上午8:58:56
	 */
	@RequestMapping("/alisms")
	public String alisms(Model model) {
		
		model.addAttribute("regionId", aliSmsProperties.getRegionId());
		model.addAttribute("accessKeyId", aliSmsProperties.getAccessKeyId());
		model.addAttribute("accessSecret", aliSmsProperties.getAccessSecret());
		model.addAttribute("smsSign", "江苏众禾");
		model.addAttribute("templateCode", "SMS_180046878");
		
		return prefix_url + "alisms.html";
	}
	
	/**
	 * 
	 * @description
	 *  	演示利用阿里云云短信SDK发送短信
	 * @return AjaxJson
	 * @author 麻木神
	 * @time 2019年12月16日 上午11:12:59
	 */
	@RequestMapping("/sendAliSms")
	@ResponseBody
	public AjaxJson sendAliSms(String regionId, String accessKeyId, String accessSecret, String smsSign, String templateCode, String phoneNo) {
		AjaxJson j = new AjaxJson();
		try {
			
			SmsUtil.sendAliSms(regionId, accessKeyId, accessSecret, phoneNo, smsSign, templateCode, "{'code':'8888'}");
			j.setMsg("短信提交成功, 是否能接受到短信，还需要运营商返回状态");
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("短信发送失败");
			log.error(e.getMessage(), e);
		}
		
		return j;
	}
	
}

package com.zhonghe.active4j.monitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.LogType;
import com.zhonghe.active4j.monitor.model.ServerInfoModel;
import com.zhonghe.active4j.monitor.service.ServerMonitorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @title ServiceController.java
 * @description 
		  服务器信息监控
 * @time  2019年12月3日 下午4:00:24
 * @author 麻木神
 * @version 1.0
*/
@Controller
@RequestMapping("/monitor/server")
@Slf4j
public class ServerMonitorController extends BaseController {
	
	@Autowired
	private ServerMonitorService serverMonitorService;
	
	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "monitor/server/";

	
	/**
	 * 
	 * @description
	 *  	服务器信息页面
	 * @params
	 *      
	 * @return String
	 * @author 麻木神
	 * @time 2019年12月3日 下午4:02:01
	 */
	@RequestMapping("/index")
	@Log(type = LogType.normal, name = "服务器监控", memo = "服务器监控")
	public String index(Model model) {
		
		ServerInfoModel server = serverMonitorService.getServer();
		model.addAttribute("server", server);
		
		return prefix_page + "index.html";
	}
	
	
	/**
	 * 
	 * @description
	 *  	获取服务器信息，供定时获取使用
	 * @params
	 * @return AjaxJson
	 * @author 麻木神
	 * @time 2019年12月4日 下午12:58:29
	 */
	@RequestMapping("/server")
	@ResponseBody
	public AjaxJson getServer(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			ServerInfoModel server = serverMonitorService.getServer();
			j.setObj(server);
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("获取服务器信息监控出错");
			log.error(e.getMessage(), e);
		}
		
		return j;
	}

}

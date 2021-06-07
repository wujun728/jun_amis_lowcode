package com.zhonghe.active4j.demo.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.annotation.Log;
import com.zhonghe.active4j.core.model.LogType;

import lombok.extern.slf4j.Slf4j;

/**
 * @title ProjectController.java
 * @description 
		首页 项目介绍页面
 * @time 2019年12月3日 上午10:09:32
 * @author chenxl
 * @version 1.0
*/
@Controller
@RequestMapping("/demo/main")
@Slf4j
public class ProjectController extends BaseController {
	
	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "home/";
	
	
	/**
	 *  
	 * @description
	 *  	介绍主页
	 * @params
	 *      
	 * @return String
	 * @author chenxl
	 * @time 2019年12月3日 上午10:55:55
	 */
	@RequestMapping("/index")
	@Log(type = LogType.normal, name = "进入控制台", memo = "进入控制台")
	public String index(Model model) {
		return prefix_page + "console.html";
	}
	
	/**
	 *  
	 * @description
	 *  	介绍主页
	 * @params
	 *      
	 * @return String
	 * @author chenxl
	 * @time 2019年12月3日 上午10:55:55
	 */
	@RequestMapping("/homepage1")
	@Log(type = LogType.normal, name = "进入控制台", memo = "进入控制台")
	public String homepage1(Model model) {
		return prefix_page + "homepage0.html";
	}
	
	/**
	 *  
	 * @description
	 *  	介绍主页
	 * @params
	 *      
	 * @return String
	 * @author chenxl
	 * @time 2019年12月3日 上午10:55:55
	 */
	@RequestMapping("/homepage2")
	@Log(type = LogType.normal, name = "进入控制台", memo = "进入控制台")
	public String homepage2(Model model) {
		return prefix_page + "homepage1.html";
	}
	
	/**
	 *  
	 * @description
	 *  	介绍主页
	 * @params
	 *      
	 * @return String
	 * @author chenxl
	 * @time 2019年12月3日 上午10:55:55
	 */
	@RequestMapping("/homepage3")
	@Log(type = LogType.normal, name = "进入控制台", memo = "进入控制台")
	public String homepage3(Model model) {
		return prefix_page + "homepage2.html";
	}
	
	
	
}

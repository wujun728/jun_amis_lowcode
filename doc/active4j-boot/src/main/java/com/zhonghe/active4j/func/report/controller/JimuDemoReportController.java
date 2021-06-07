package com.zhonghe.active4j.func.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.func.timer.controller.QuartzJobLogController;

import lombok.extern.slf4j.Slf4j;

/**
 * @title JimuDemoReportController.java
 * @description 
		  jimu报表 演示实例
 * @time  2021年4月16日 下午3:29:37
 * @author 麻木神
 * @version 1.0
*/
@Controller
@RequestMapping("/func/report/demo")
@Slf4j
public class JimuDemoReportController extends BaseController {

	private static final String prefix_page = "func/report/";
	
	/**
	 * 
	 * @description
	 *  	跳转到demo01
	 * @return String
	 * @author 麻木神
	 * @time 2021年4月16日 下午3:47:13
	 */
	@RequestMapping("/demo01")
	public String list(Model model) {
		return prefix_page + "demo01.html";
	}
}

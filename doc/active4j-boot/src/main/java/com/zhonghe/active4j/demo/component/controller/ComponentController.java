package com.zhonghe.active4j.demo.component.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhonghe.active4j.common.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

/**
 * @title ComponentController.java
 * @description 
		  示例组件
 * @time  2019年12月9日 下午9:54:06
 * @author 麻木神
 * @version 1.0
*/
@Controller
@RequestMapping("/comp")
@Slf4j
public class ComponentController extends BaseController {
	
	public static final String prefix_url = "comps/";
	
	@RequestMapping("/{comp}/{page}")
	public String index(@PathVariable String comp, @PathVariable String page, Model model) {
		
		log.info(prefix_url + comp + "/" + page);

		
		return prefix_url + comp + "/" + page + ".html";
	}
	
	
}

package com.zhonghe.active4j.func.layedit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhonghe.active4j.common.controller.BaseController;

/**
 * 
 * @title LayEditController.java
 * @description 
		富文本编辑器管理
 * @time  2019年12月13日 上午10:19:03
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/layedit")
public class LayEditController extends BaseController {

	private static final String prefix_page = "func/layedit/";
	
	/**
	 * 
	 * @description
	 *  	跳转layedit 编辑器页
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月13日 上午10:19:52
	 */
	@RequestMapping("/layedit")
	public String layedit(Model model) {
		return prefix_page + "layedit.html";
	}
	
	/**
	 * 跳转kz.layedit 编辑器页
	 * @description
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月13日 下午4:54:54
	 */
	@RequestMapping("/kzlayedit")
	public String kzLayedit(Model model) {
		return prefix_page + "kzlayedit.html";
	}
	
	/**
	 * 
	 * @description
	 *  	跳转tinymce 编辑器页
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月13日 下午1:37:19
	 */
	@RequestMapping("/tinymce")
	public String tinymce(Model model) {
		return prefix_page + "tinymce.html";
	}
	
}

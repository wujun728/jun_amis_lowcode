package com.thinkingcao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年11月22日 上午11:18:56
 * </pre>
 */
@RestController
public class UserController {
	@RequestMapping("/index")
	public String index() {
		return "张三";
	}
}

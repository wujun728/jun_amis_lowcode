package com.htmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	@RequestMapping(value = "/{code}")
	public String index(@PathVariable String code, Model model) {
		return "error/" + code;
	}
}

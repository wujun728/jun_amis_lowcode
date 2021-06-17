package com.jun.plugin.modules.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/403")
	public String noPermission(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		return "error/403";
	}
}

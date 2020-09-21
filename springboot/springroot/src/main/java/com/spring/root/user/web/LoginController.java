package com.spring.root.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.root.user.service.UserService;

@Controller
@RequestMapping("/")  
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
    public String home() {
		return "sys/index";
    }
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response,Model model){
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String msg = userService.login(loginName, password);
		model.addAttribute("msg", msg);
		return "sys/result";
	}
	
	
}

package com.fish.web.admin;

import com.fish.cons.REST;
import com.fish.service.AdminService;
import com.fish.service.MessageService;
import com.fish.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/19
 * Time: 17:09
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = REST.ADMIN, method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("user") != null) {
            modelMap.addAttribute("msgs", messageService.findAllMsg());
            return "back/message";
        }
        modelMap.addAttribute("error", "请先登录");
        return "front/index";
    }

    @RequestMapping(value = REST.LOGIN, method = RequestMethod.POST)
    public String postLogin(LoginCommand loginCommand, ModelMap modelMap, HttpSession session) {
        String email = loginCommand.getEmail();
        if (!adminService.exists(email)) {
            modelMap.addAttribute("error", "用户不存在");
            return "forward:/";
        }

        String password = Crypto.md5Hex(loginCommand.getPassword());
        if (!adminService.valid(email, password)) {
            modelMap.addAttribute("error", "密码错误");
            return "forward:/";
        }

        session.setAttribute("user", email);
        return "redirect:/admin/mgr";
    }

    @RequestMapping(value = REST.LOGOUT, method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = REST.PASSCHECK, method = RequestMethod.POST)
    @ResponseBody
    public String passCheck(PassCheck passCheck) {
        if (adminService.valid(passCheck.getEmail(),
                Crypto.md5Hex(passCheck.getPass()))) {
            return "Ok";
        }
        return "Fail";
    }
}

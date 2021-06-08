package com.fish.web.admin;

import com.fish.cons.REST;
import com.fish.domain.Admin;
import com.fish.service.AdminInfoService;
import com.fish.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/22
 * Time: 10:55
 */
@Controller
@RequestMapping(REST.ADMIN_ADMINS)
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap modelMap) {
        modelMap.addAttribute("title", "管理员");
        modelMap.addAttribute("info", getAdmin(session));
        return "back/admins";
    }

    private Admin getAdmin(HttpSession session) {
        String email = (String) session.getAttribute("user");
        return adminInfoService.findAdminByEmail(email);
    }

    @RequestMapping(value = "/passupdate", method = RequestMethod.POST)
    @ResponseBody
    public String index(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
        String newPass = Crypto.md5Hex(request.getParameter("passnew"));
        if (adminInfoService.updatePass(newPass, (String) session.getAttribute("user"))) {
//            modelMap.addAttribute("mgrSuccess", "密码更新成功");
            return "Ok";
        }
        return "Fail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap modelMap, HttpSession session, UpdateCommand updateCommand) {
        if (adminInfoService.update(updateCommand, getAdmin(session).getId())) {
            modelMap.addAttribute("adminSuccess", "更改成功");
        } else {
            modelMap.addAttribute("adminError", "更改失败");
        }

        session.setAttribute("user", updateCommand.getEmail());
        return index(session, modelMap);
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        return adminInfoService.findAdminByEmail(email).getEmail() == null ? "Ok" : "Fail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ModelMap modelMap, HttpSession session, AdminCommand adminCommand) {
        adminCommand.setPassword(Crypto.md5Hex(adminCommand.getPassword()));

        if (adminInfoService.add(adminCommand)) {
            modelMap.addAttribute("adminSuccess", "添加成功");
        } else {
            modelMap.addAttribute("adminError", "添加失败");
        }

        return index(session, modelMap);
    }
}

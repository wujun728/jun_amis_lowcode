package com.fish.web.home;

import com.fish.cons.REST;
import com.fish.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/23
 * Time: 14:47
 */
@Controller
@RequestMapping(REST.MSG)
public class MsgController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public String index(HttpServletRequest request, ModelMap modelMap) {
        String email = request.getParameter("email");
        String content = request.getParameter("content");
        if (messageService.addMsg(email, content, request.getRemoteAddr())) {
            modelMap.addAttribute("msgError", "留言成功");
        } else {
            modelMap.addAttribute("msgError", "留言成功");
        }
        return "front/about";
    }
}

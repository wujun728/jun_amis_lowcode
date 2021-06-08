package com.fish.web.admin;

import com.fish.cons.REST;
import com.fish.domain.Msg;
import com.fish.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/22
 * Time: 15:41
 */
@Controller
@RequestMapping(REST.ADMIN_MSG)
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        List<Msg> msgs = messageService.findAllMsg();
        modelMap.addAttribute("msgs", msgs);
        modelMap.addAttribute("title", "留言");
        return "back/message";
    }

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String msgsByPage(@PathVariable("page") int page, ModelMap modelMap) {
        List<Msg> msgs = messageService.findMsgByPage(page);
        modelMap.addAttribute("msgs", msgs);
        return "back/message";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMsg(@PathVariable("id") int id, ModelMap modelMap) {
        if (messageService.deleteById(id)) {
            modelMap.addAttribute("mgrSuccess", "删除成功");
        } else {
            modelMap.addAttribute("mgrError", "删除失败");
        }
        List<Msg> msgs = messageService.findAllMsg();
        modelMap.addAttribute("msgs", msgs);
        return "/back/message";
    }
}

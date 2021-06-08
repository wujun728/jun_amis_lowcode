package com.fish.web.home;

import com.fish.cons.REST;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/19
 * Time: 17:10
 */
@Controller
@RequestMapping(REST.ABOUT)
public class AboutController {
    @RequestMapping(method = RequestMethod.GET)
    public String about(ModelMap modelMap) {
        modelMap.addAttribute("title", "About");
        return "front/about";
    }
}

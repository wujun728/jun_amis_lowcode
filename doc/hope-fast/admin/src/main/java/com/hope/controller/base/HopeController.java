package com.hope.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面跳转路由
 *
 * @author aodeng
 **/
@Controller
public class HopeController {

    /***
     * 首页
     * @return
     */
    @GetMapping(value = {"/", "/common/index", "/index"})
    public String index() {
        return "common/index";
    }

    /***
     * 登录
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "common/login";
    }

    @GetMapping("/forgetpwd")
    public String forgetpwd() {
        return "common/forgetpwd";
    }

    /**
     * 其他
     *
     * @author aodeng
     */
    @GetMapping("/index_v3")
    public String index_v3() {
        return "admin/index/index_v3";
    }

    @GetMapping("/equipment/equipment")
    public String equipment() {
        return "admin/equipment/equipment";
    }

    @GetMapping("/equipment/add")
    public String equipmentAdd() {
        return "admin/equipment/add";
    }

    @GetMapping("/equipment/edit/{id}")
    public String equipmentUpdate(@PathVariable("id") String id, ModelMap mmap) {
        //查询数据库数据
        //这里修改传id，还是使用后端根据id查询返回到编辑页面 后期可以优化为前端存本地缓存的方式 独立前后端模块降低耦合度
        mmap.put("equipmentInfo","");
        return "admin/equipment/edit";
    }

    @GetMapping("/order/order")
    public String order() {
        return "admin/order/order";
    }

    @GetMapping("/order/detail/{id}")
    public String orderDetail(@PathVariable("id") String id, ModelMap mmap) {
        //查询数据库数据
        mmap.put("orderDTO","");
        return "admin/order/order_detail";
    }

    @GetMapping("/profile")
    public String profile() {
        return "common/profile";
    }

    @GetMapping("/avatar")
    public String avatar() {
        return "common/avatar";
    }
}
package com.ruoyi.web.utils;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.MenuService;
import com.ruoyi.project.system.user.domain.User;

import java.util.List;

/**
 * @Title UserUtils
 * @Description
 * @Date 2020/7/24  15:09
 */
public class UserUtils {

    /*获取用户菜单*/
    public static List<Menu> listUserMenu(){
        User user = ShiroUtils.getSysUser();
        List<com.ruoyi.project.system.menu.domain.Menu> menus= SpringUtils.getBean(MenuService.class).selectMenusByUser(user);
        return menus;
    }
    /*获取当前用户*/
    public static User getUser(){
        User user = ShiroUtils.getSysUser();
        return user;
    }
}

package com.jun.plugin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.service.MenuService;

@SpringBootTest
class MenuInitUtils {

    @Autowired
    private MenuService menuService;

    @Test
    void initMenu() {
        menuService.save(new Menu(1, 0, "topmenu", "business", "业务管理", "fa fa-newspaper-o", "", null, 0, 1, 1));
        menuService.save(new Menu(2, 0, "topmenu", "system", "系统管理", "fa fa-gear", "", null, 0, 2, 1));
        menuService.save(new Menu(3, 0, "topmenu", "nb666", "nb666个人简介", "fa fa-gear", "", null, 0, 3, 1));

        menuService.save(new Menu(4, 1, "leftmenu", "business", "基础数据管理", "fa fa-gear", "", null, 0, 4, 1));
        menuService.save(new Menu(5, 1, "leftmenu", "business", "进货管理", "fa fa-gear", "", null, 0, 5, 1));
        menuService.save(new Menu(6, 1, "leftmenu", "business", "销售管理", "fa fa-gear", "", null, 0, 6, 1));

        menuService.save(new Menu(7, 5, "leftmenu", "business", "客户管理", "fa fa-gear", "", null, 0, 7, 1));
        menuService.save(new Menu(8, 5, "leftmenu", "business", "供应商管理", "fa fa-gear", "", null, 0, 8, 1));
        menuService.save(new Menu(9, 5, "leftmenu", "business", "商品管理", "fa fa-gear", "", null, 0, 9, 1));

        menuService.save(new Menu(10, 5, "leftmenu", "business", "商品进货", "fa fa-gear", "", null, 0, 10, 1));
        menuService.save(new Menu(11, 5, "leftmenu", "business", "商品退货", "fa fa-gear", "", null, 0, 11, 1));

        menuService.save(new Menu(12, 6, "leftmenu", "business", "商品销售", "fa fa-gear", "", null, 0, 12, 1));
        menuService.save(new Menu(13, 6, "leftmenu", "business", "销售退货", "fa fa-gear", "", null, 0, 13, 1));


        menuService.save(new Menu(14, 3, "leftmenu", "system", "系统管理", "fa fa-gear", "", null, 0, 14, 1));
        menuService.save(new Menu(15, 3, "leftmenu", "system", "其它管理", "fa fa-gear", "", null, 0, 15, 1));

        menuService.save(new Menu(16, 14, "leftmenu", "system", "部门管理", "fa fa-gear", "", null, 0, 16, 1));
        menuService.save(new Menu(17, 14, "leftmenu", "system", "菜单管理", "fa fa-gear", "", null, 0, 17, 1));
        menuService.save(new Menu(18, 14, "leftmenu", "system", "角色管理", "fa fa-gear", "", null, 0, 18, 1));
        menuService.save(new Menu(19, 14, "leftmenu", "system", "用户管理", "fa fa-gear", "", null, 0, 19, 1));

        menuService.save(new Menu(20, 15, "leftmenu", "system", "登陆日志", "fa fa-gear", "", null, 0, 20, 1));
        menuService.save(new Menu(21, 15, "leftmenu", "system", "数据源监控", "fa fa-gear", "", null, 0, 21, 1));
        menuService.save(new Menu(22, 15, "leftmenu", "system", "系统公告", "fa fa-gear", "", null, 0, 22, 1));
        menuService.save(new Menu(23, 15, "leftmenu", "system", "图标管理", "fa fa-gear", "", null, 0, 23, 1));


        System.out.println("初始化成功");


    }

    //权限
    @Test
    void initPermisstion() {
        menuService.save(new Menu(24, 7, "permission", "customer:query", "客户查询", 24, 1));
        menuService.save(new Menu(25, 7, "permission", "customer:add", "客户添加", 25, 1));
        menuService.save(new Menu(26, 7, "permission", "customer:delete", "客户删除", 26, 1));
        menuService.save(new Menu(27, 7, "permission", "customer:update", "客户更新", 27, 1));
        menuService.save(new Menu(28, 3, "leftmenu", "nb666", "哼", "fa fa-gear", "", null, 0, 28, 1));
    }

}

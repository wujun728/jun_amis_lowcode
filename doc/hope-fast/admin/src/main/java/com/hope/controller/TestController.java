package com.hope.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.model.bean.SysUserBean;
import com.hope.service.ISysUserService;
import com.hope.utils.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:aodeng
 * @create:2018-12-10 20:13
 **/
@RestController
@Slf4j
public class TestController {

    @Autowired
    private ISysUserService iSysUserService;

    @RequestMapping("test")
    public String test() {
        IPage<SysUserBean> list = iSysUserService.selectUserListPageVo(new Page(1, 10), new SysUserBean());
        System.out.println(list);
        return "1";
    }


    /*一下为模拟接口数据 不关联数据库*/
    @RequestMapping("passPCApi/order/orderCount")
    public AjaxResult test1(String token){
        Map<String,Object> map=new HashMap<String,Object>(16);
        map.put("repairsOrderTotal","1");
        map.put("repairsOrderStatus1Total","1");
        map.put("repairsOrderStatus2Total","2");
        map.put("repairsOrderStatus3Total","3");
        map.put("repairsOrderStatus4Total","4");

        map.put("roleId1","1");
        map.put("roleId2","2");
        map.put("roleId3","3");
        map.put("roleId4","4");
        return AjaxResult.success(map);
    }
}

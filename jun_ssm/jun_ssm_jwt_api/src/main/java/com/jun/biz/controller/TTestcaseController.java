package com.jun.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.common.ReturnT;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* 用例信息
*
* Created by wujun on '2021-03-24 01:01:30'.
*/
@Controller
public class TTestcaseController {

    @Resource
    private TTestcaseService tTestcaseService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(TTestcase tTestcase){
        return tTestcaseService.insert(tTestcase);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return tTestcaseService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(TTestcase tTestcase){
        return tTestcaseService.update(tTestcase);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public TTestcase load(int id){
        return tTestcaseService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return tTestcaseService.pageList(offset, pagesize);
    }

}

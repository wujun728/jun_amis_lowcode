package com.reading.app.controller;

import com.reading.app.domain.ReIntegral;
import com.reading.app.service.IReIntegralService;
import com.reading.common.core.controller.BaseController;
import com.reading.common.core.domain.AjaxResult;
import com.reading.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.reading.app.domain.Integral;
import com.reading.app.service.IIntegralService;

import java.util.List;

/** 签到
 * @author cj
 */
@Controller
@RequestMapping("/app/integral")
public class AppIntegralController  extends BaseController {

    @Autowired
    IIntegralService iIntegralService;


    @Autowired
    private IReIntegralService reIntegralService;

    /**
     * 查询积分列表
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(ReIntegral reIntegral)
    {
        System.out.println("select integral");
        List<ReIntegral> list = reIntegralService.selectReIntegralList(reIntegral);
        return AjaxResult.success(list);
    }

    @ResponseBody
    @RequestMapping(value = "/sign" ,method = RequestMethod.POST)
    public AjaxResult sign(@RequestBody Integral integral){
        int ret = iIntegralService.sing(integral);
        if(ret>0){
            return AjaxResult.success("签到成功");
        }
        return AjaxResult.error();
    }
}

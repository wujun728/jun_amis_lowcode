package com.jun.plugin.app.controller.ow;

import com.jun.plugin.app.common.base.BaseController;
import com.jun.plugin.app.common.page.ResultData;
import com.jun.plugin.ow.domain.Huiming;
import com.jun.plugin.ow.service.IHuimingService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 惠民服务 信息操作处理
 * 
 * @author admin
 * @date 2018-12-13
 */
@Controller
@RequestMapping("/ow/huiming")
public class HuimingController extends BaseController
{
    private String prefix = "ow/huiming";
	
	@Autowired
	private IHuimingService huimingService;
	
	@RequiresPermissions("ow:huiming:view")
	@GetMapping()
	public String huiming()
	{
	    return prefix + "/huiming";
	}


	/**
	 * 查询惠民服务列表For 官网
	 */

	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Huiming huiming)
	{
		startPage();
        List<Huiming> list = huimingService.selectHuimingListForOw(huiming);
		return getDataPage(list);
	}

    /**
     * 详情
     */

    @PostMapping("/getById")
    @ResponseBody
    public Huiming editSave(@RequestBody Huiming huiming)
    {
        return  huimingService.getHuimingByIds(huiming.getId());
    }

	
}

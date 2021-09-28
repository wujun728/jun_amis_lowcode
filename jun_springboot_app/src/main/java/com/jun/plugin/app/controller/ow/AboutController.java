package com.jun.plugin.app.controller.ow;

import com.jun.plugin.app.common.base.BaseController;
import com.jun.plugin.ow.domain.About;
import com.jun.plugin.ow.service.IAboutService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 关于我们 信息操作处理
 * 
 * @author admin
 * @date 2018-12-13
 */
@Controller
@RequestMapping("/ow/about")
public class AboutController extends BaseController
{
    private String prefix = "ow/about";
	
	@Autowired
	private IAboutService aboutService;
	
	@RequiresPermissions("ow:about:view")
	@GetMapping()
	public String about()
	{
	    return prefix + "/about";
	}
	


    /**
     * 详情(只有一条，不允许新增修改，ID写死即可)
     */

    @PostMapping("/getById")
    @ResponseBody
    public About editSave(@RequestBody About about)
    {
    	about.setId(2L);
        return  aboutService.getAboutByIds(about.getId());
    }


}

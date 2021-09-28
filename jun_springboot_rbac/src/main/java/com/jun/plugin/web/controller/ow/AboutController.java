package com.jun.plugin.web.controller.ow;

import com.jun.plugin.common.annotation.Log;
import com.jun.plugin.common.base.AjaxResult;
import com.jun.plugin.common.enums.BusinessType;
import com.jun.plugin.common.utils.ExcelUtil;
import com.jun.plugin.framework.util.ShiroUtils;
import com.jun.plugin.framework.web.base.BaseController;
import com.jun.plugin.framework.web.page.TableDataInfo;
import com.jun.plugin.ow.domain.About;
import com.jun.plugin.ow.service.IAboutService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * 查询关于我们列表
	 */
	@RequiresPermissions("ow:about:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(About about)
	{
		startPage();
        List<About> list = aboutService.selectAboutList(about);
		return getDataTable(list);
	}

	/**
	 * 查询关于我们列表For 官网
	 */
	/*
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  About about)
	{
		startPage();
        List<About> list = aboutService.selectAboutListForOw(about);
		return getDataPage(list);
	}
	*/
    /**
     * 详情
     */
    /*
    @PostMapping("/getById")
    @ResponseBody
    public Jobs editSave(@RequestBody About about)
    {
        return  aboutService.getaboutByIds(about.getId());
    }
	*/
	/**
	 * 导出关于我们列表
	 */
	@RequiresPermissions("ow:about:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(About about)
    {
    	List<About> list = aboutService.selectAboutList(about);
        ExcelUtil<About> util = new ExcelUtil<About>(About.class);
        return util.exportExcel(list, "about");
    }
	
	/**
	 * 新增关于我们
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存关于我们
	 */
	@RequiresPermissions("ow:about:add")
	@Log(title = "关于我们", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(About about)
	{
		String content = about.getContent();

		about.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(aboutService.insertAbout(about));
	}

	/**
	 * 修改关于我们
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		About about = aboutService.selectAboutById(id);
		mmap.put("about", about);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存关于我们
	 */
	@RequiresPermissions("ow:about:edit")
	@Log(title = "关于我们", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(About about)
	{
		about.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(aboutService.updateAbout(about));
	}
	
	/**
	 * 删除关于我们
	 */
	@RequiresPermissions("ow:about:remove")
	@Log(title = "关于我们", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(aboutService.deleteAboutByIds(ids));
	}
	
}

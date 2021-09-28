package com.royal.web.controller.ow;

import com.royal.common.annotation.Log;
import com.royal.common.base.AjaxResult;
import com.royal.common.enums.BusinessType;
import com.royal.common.utils.ExcelUtil;
import com.royal.framework.util.ShiroUtils;
import com.royal.framework.web.base.BaseController;
import com.royal.framework.web.page.TableDataInfo;
import com.royal.ow.domain.Slide;
import com.royal.ow.service.ISlideService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 幻灯片 信息操作处理
 * 
 * @author royal
 * @date 2018-12-13
 */
@Controller
@RequestMapping("/ow/slide")
public class SlideController extends BaseController
{
    private String prefix = "ow/slide";
	
	@Autowired
	private ISlideService slideService;
	
	@RequiresPermissions("ow:slide:view")
	@GetMapping()
	public String slide()
	{
	    return prefix + "/slide";
	}
	
	/**
	 * 查询幻灯片列表
	 */
	@RequiresPermissions("ow:slide:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Slide slide)
	{
		startPage();
        List<Slide> list = slideService.selectSlideList(slide);
		return getDataTable(list);
	}

	/**
	 * 查询幻灯片列表For 官网
	 */
	/*
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Slide slide)
	{
		startPage();
        List<Slide> list = slideService.selectSlideListForOw(slide);
		return getDataPage(list);
	}
	*/
    /**
     * 详情
     */
    /*
    @PostMapping("/getById")
    @ResponseBody
    public Jobs editSave(@RequestBody Slide slide)
    {
        return  slideService.getslideByIds(slide.getId());
    }
	*/
	/**
	 * 导出幻灯片列表
	 */
	@RequiresPermissions("ow:slide:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Slide slide)
    {
    	List<Slide> list = slideService.selectSlideList(slide);
        ExcelUtil<Slide> util = new ExcelUtil<Slide>(Slide.class);
        return util.exportExcel(list, "slide");
    }
	
	/**
	 * 新增幻灯片
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存幻灯片
	 */
	@RequiresPermissions("ow:slide:add")
	@Log(title = "幻灯片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Slide slide)
	{
		slide.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(slideService.insertSlide(slide));
	}

	/**
	 * 修改幻灯片
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Slide slide = slideService.selectSlideById(id);
		mmap.put("slide", slide);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存幻灯片
	 */
	@RequiresPermissions("ow:slide:edit")
	@Log(title = "幻灯片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Slide slide)
	{
		slide.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(slideService.updateSlide(slide));
	}
	
	/**
	 * 删除幻灯片
	 */
	@RequiresPermissions("ow:slide:remove")
	@Log(title = "幻灯片", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(slideService.deleteSlideByIds(ids));
	}
	
}

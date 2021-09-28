package com.royal.web.controller.ow;

import com.royal.common.annotation.Log;
import com.royal.common.base.AjaxResult;
import com.royal.common.enums.BusinessType;
import com.royal.common.utils.ExcelUtil;
import com.royal.framework.util.ShiroUtils;
import com.royal.framework.web.base.BaseController;
import com.royal.framework.web.page.TableDataInfo;
import com.royal.ow.domain.Huiming;
import com.royal.ow.service.IHuimingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 惠民服务 信息操作处理
 * 
 * @author royal
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
	 * 查询惠民服务列表
	 */
	@RequiresPermissions("ow:huiming:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Huiming huiming)
	{
		startPage();
        List<Huiming> list = huimingService.selectHuimingList(huiming);
		return getDataTable(list);
	}

	/**
	 * 查询惠民服务列表For 官网
	 */
	/*
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Huiming huiming)
	{
		startPage();
        List<Huiming> list = huimingService.selectHuimingListForOw(huiming);
		return getDataPage(list);
	}
	*/
    /**
     * 详情
     */
    /*
    @PostMapping("/getById")
    @ResponseBody
    public Jobs editSave(@RequestBody Huiming huiming)
    {
        return  huimingService.gethuimingByIds(huiming.getId());
    }
	*/
	/**
	 * 导出惠民服务列表
	 */
	@RequiresPermissions("ow:huiming:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Huiming huiming)
    {
    	List<Huiming> list = huimingService.selectHuimingList(huiming);
        ExcelUtil<Huiming> util = new ExcelUtil<Huiming>(Huiming.class);
        return util.exportExcel(list, "huiming");
    }
	
	/**
	 * 新增惠民服务
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存惠民服务
	 */
	@RequiresPermissions("ow:huiming:add")
	@Log(title = "惠民服务", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Huiming huiming)
	{
		huiming.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(huimingService.insertHuiming(huiming));
	}

	/**
	 * 修改惠民服务
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Huiming huiming = huimingService.selectHuimingById(id);
		mmap.put("huiming", huiming);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存惠民服务
	 */
	@RequiresPermissions("ow:huiming:edit")
	@Log(title = "惠民服务", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Huiming huiming)
	{
		huiming.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(huimingService.updateHuiming(huiming));
	}
	
	/**
	 * 删除惠民服务
	 */
	@RequiresPermissions("ow:huiming:remove")
	@Log(title = "惠民服务", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(huimingService.deleteHuimingByIds(ids));
	}
	
}

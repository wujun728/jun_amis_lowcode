package com.royal.web.controller.ow;

import com.royal.common.annotation.Log;
import com.royal.common.base.AjaxResult;
import com.royal.common.enums.BusinessType;
import com.royal.common.utils.ExcelUtil;
import com.royal.framework.util.ShiroUtils;
import com.royal.framework.web.base.BaseController;
import com.royal.framework.web.page.TableDataInfo;
import com.royal.ow.domain.Equipment;
import com.royal.ow.service.IEquipmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备介绍 信息操作处理
 * 
 * @author royal
 * @date 2018-12-13
 */
@Controller
@RequestMapping("/ow/equipment")
public class EquipmentController extends BaseController
{
    private String prefix = "ow/equipment";
	
	@Autowired
	private IEquipmentService equipmentService;
	
	@RequiresPermissions("ow:equipment:view")
	@GetMapping()
	public String equipment()
	{
	    return prefix + "/equipment";
	}
	
	/**
	 * 查询设备介绍列表
	 */
	@RequiresPermissions("ow:equipment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Equipment equipment)
	{
		startPage();
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
		return getDataTable(list);
	}

	/**
	 * 查询设备介绍列表For 官网
	 */
	/*
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Equipment equipment)
	{
		startPage();
        List<Equipment> list = equipmentService.selectEquipmentListForOw(equipment);
		return getDataPage(list);
	}
	*/
    /**
     * 详情
     */
    /*
    @PostMapping("/getById")
    @ResponseBody
    public Jobs editSave(@RequestBody Equipment equipment)
    {
        return  equipmentService.getequipmentByIds(equipment.getId());
    }
	*/
	/**
	 * 导出设备介绍列表
	 */
	@RequiresPermissions("ow:equipment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Equipment equipment)
    {
    	List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        ExcelUtil<Equipment> util = new ExcelUtil<Equipment>(Equipment.class);
        return util.exportExcel(list, "equipment");
    }
	
	/**
	 * 新增设备介绍
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备介绍
	 */
	@RequiresPermissions("ow:equipment:add")
	@Log(title = "设备介绍", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Equipment equipment)
	{
		equipment.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(equipmentService.insertEquipment(equipment));
	}

	/**
	 * 修改设备介绍
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Equipment equipment = equipmentService.selectEquipmentById(id);
		mmap.put("equipment", equipment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备介绍
	 */
	@RequiresPermissions("ow:equipment:edit")
	@Log(title = "设备介绍", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Equipment equipment)
	{
		equipment.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(equipmentService.updateEquipment(equipment));
	}
	
	/**
	 * 删除设备介绍
	 */
	@RequiresPermissions("ow:equipment:remove")
	@Log(title = "设备介绍", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(equipmentService.deleteEquipmentByIds(ids));
	}
	
}

package com.royal.app.controller.ow;

import com.royal.app.common.base.BaseController;
import com.royal.app.common.page.ResultData;
import com.royal.ow.domain.Equipment;
import com.royal.ow.service.IEquipmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	 * 查询设备介绍列表For 官网
	 */

	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Equipment equipment)
	{
		startPage();
        List<Equipment> list = equipmentService.selectEquipmentListForOw(equipment);
		return getDataPage(list);
	}

    /**
     * 详情
     */

    @PostMapping("/getById")
    @ResponseBody
    public Equipment editSave(@RequestBody Equipment equipment)
    {
        return  equipmentService.getEquipmentByIds(equipment.getId());
    }


	
}

package com.royal.ow.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.royal.common.annotation.Log;
import com.royal.common.enums.BusinessType;
import com.royal.ow.domain.Customer;
import com.royal.ow.service.ICustomerService;
import com.royal.framework.util.ShiroUtils;
import com.royal.framework.web.base.BaseController;
import com.royal.framework.web.page.TableDataInfo;
import com.royal.common.base.AjaxResult;
import com.royal.common.utils.ExcelUtil;

/**
 * 客户 信息操作处理
 * 
 * @author royal
 * @date 2021-09-25
 */
@Controller
@RequestMapping("/ow/customer")
public class CustomerController extends BaseController
{
    private String prefix = "ow/customer";
	
	@Autowired
	private ICustomerService customerService;
	
	@RequiresPermissions("ow:customer:view")
	@GetMapping()
	public String customer()
	{
	    return prefix + "/customer";
	}
	
	/**
	 * 查询客户列表
	 */
	@RequiresPermissions("ow:customer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Customer customer)
	{
		startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
		return getDataTable(list);
	}

	/**
	 * 查询客户列表For 官网
	 */
	/*
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody  Customer customer)
	{
		startPage();
        List<Customer> list = customerService.selectCustomerListForOw(customer);
		return getDataPage(list);
	}
	*/
    /**
     * 详情
     */
    /*
    @PostMapping("/getById")
    @ResponseBody
    public Customer editSave(@RequestBody Customer customer)
    {
        return  customerService.getCustomerByIds(customer.getId());
    }
	*/
	/**
	 * 导出客户列表
	 */
	@RequiresPermissions("ow:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer)
    {
    	List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }
	
	/**
	 * 新增客户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存客户
	 */
	@RequiresPermissions("ow:customer:add")
	@Log(title = "客户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Customer customer)
	{
		customer.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(customerService.insertCustomer(customer));
	}

	/**
	 * 修改客户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Customer customer = customerService.selectCustomerById(id);
		mmap.put("customer", customer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户
	 */
	@RequiresPermissions("ow:customer:edit")
	@Log(title = "客户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Customer customer)
	{
		customer.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(customerService.updateCustomer(customer));
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("ow:customer:remove")
	@Log(title = "客户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(customerService.deleteCustomerByIds(ids));
	}
	
}

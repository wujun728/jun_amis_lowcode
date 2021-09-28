package com.royal.web.controller.ow;

import com.royal.common.annotation.Log;
import com.royal.common.base.AjaxResult;
import com.royal.common.enums.BusinessType;
import com.royal.common.utils.ExcelUtil;
import com.royal.framework.util.ShiroUtils;
import com.royal.framework.web.base.BaseController;
import com.royal.framework.web.page.TableDataInfo;
import com.royal.ow.domain.Jobs;
import com.royal.ow.service.IJobsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招贤纳士 信息操作处理
 * 
 * @author royal
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/ow/jobs")
public class JobsController extends BaseController
{
    private String prefix = "ow/jobs";
	
	@Autowired
	private IJobsService jobsService;
	
	@RequiresPermissions("ow:jobs:view")
	@GetMapping()
	public String jobs()
	{
	    return prefix + "/jobs";
	}
	
	/**
	 * 查询招贤纳士列表
	 */
	@RequiresPermissions("ow:jobs:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Jobs jobs)
	{
		startPage();
        List<Jobs> list = jobsService.selectJobsList(jobs);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出招贤纳士列表
	 */
	@RequiresPermissions("ow:jobs:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Jobs jobs)
    {
    	List<Jobs> list = jobsService.selectJobsList(jobs);
        ExcelUtil<Jobs> util = new ExcelUtil<Jobs>(Jobs.class);
        return util.exportExcel(list, "jobs");
    }
	
	/**
	 * 新增招贤纳士
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存招贤纳士
	 */
	@RequiresPermissions("ow:jobs:add")
	@Log(title = "招贤纳士", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Jobs jobs)
	{
		jobs.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(jobsService.insertJobs(jobs));
	}

	/**
	 * 修改招贤纳士
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Jobs jobs = jobsService.selectJobsById(id);
		mmap.put("jobs", jobs);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存招贤纳士
	 */
	@RequiresPermissions("ow:jobs:edit")
	@Log(title = "招贤纳士", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Jobs jobs)
	{
		jobs.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(jobsService.updateJobs(jobs));
	}
	
	/**
	 * 删除招贤纳士
	 */
	@RequiresPermissions("ow:jobs:remove")
	@Log(title = "招贤纳士", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jobsService.deleteJobsByIds(ids));
	}
	
}

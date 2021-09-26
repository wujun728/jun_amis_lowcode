package com.royal.app.controller.ow;

import com.royal.app.common.base.BaseController;
import com.royal.app.common.page.ResultData;
import com.royal.ow.domain.Jobs;
import com.royal.ow.service.IJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@Autowired
	private IJobsService jobsService;
	
	/**
	 * 查询招贤纳士列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody Jobs jobs)
	{
		startPage();
        List<Jobs> list = jobsService.selectJobsListForOw(jobs);
		return getDataPage(list);
	}

	/**
	 * 详情
	 */
	@PostMapping("/getById")
	@ResponseBody
	public Jobs editSave(@RequestBody Jobs jobs)
	{
		return  jobsService.getNewsByIds(jobs.getId());
	}
}

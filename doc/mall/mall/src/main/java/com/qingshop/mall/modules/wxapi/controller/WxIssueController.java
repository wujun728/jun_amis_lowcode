package com.qingshop.mall.modules.wxapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallIssue;
import com.qingshop.mall.modules.mall.service.IMallIssueService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/issue")
public class WxIssueController extends BaseController{
	
	@Autowired
	private IMallIssueService mallIssueService;
	
	/**
	 * 我的帮助列表
	 */
	@ApiOperation(value = "我的帮助列表",response = Rest.class)
	@GetMapping("/help")
	public Rest list() {
		Map<String, Object> data = new HashMap<String, Object>();
		List<MallIssue> issuesList = mallIssueService.list();
		data.put("list", issuesList);
		return Rest.okData(data);
	}
}

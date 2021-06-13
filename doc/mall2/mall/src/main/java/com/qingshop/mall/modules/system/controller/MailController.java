package com.qingshop.mall.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysMail;
import com.qingshop.mall.modules.system.service.ISysMailService;
import com.qingshop.mall.modules.system.service.MailService;

/**
 * 邮件记录表 前端控制器
 */
@Controller
@RequestMapping("/system/mail")
public class MailController extends BaseController {

	@Autowired
	private ISysMailService sysMailService;

	@Autowired
	private MailService mailService;

	/**
	 * 配置文件中我的qq邮箱
	 */
	@Value("${spring.mail.from}")
	private String from;

	@RequiresPermissions("listMail")
	@RequestMapping("/list")
	public String list() {
		return "/system/mail/list";
	}

	/**
	 * 分页查询
	 */
	@RequiresPermissions("listMail")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysMail> page = getPage(pageIndex, length);
		QueryWrapper<SysMail> ew = new QueryWrapper<SysMail>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("title", search);
		}
		IPage<SysMail> pageData = sysMailService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("addMail")
	@RequestMapping("/add")
	public String add() {
		return "system/mail/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addMail")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysMail sysMail) {
		if (StringUtils.isEmpty(sysMail.getToMail())) {
			Rest.failure("收件人输入错误");
		}
		if (StringUtils.isEmpty(sysMail.getTitle())) {
			Rest.failure("邮件标题输入错误");
		}
		if (StringUtils.isEmpty(sysMail.getContent())) {
			Rest.failure("邮件内容输入错误");
		}
		mailService.sendHtmlMail(sysMail.getToMail().split(";"), sysMail.getTitle(), sysMail.getContent());
		sysMail.setMailId(DistributedIdWorker.nextId());
		sysMail.setFromMail(from);
		sysMailService.save(sysMail);
		return Rest.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("deleteMail")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		sysMailService.removeById(id);
		return Rest.ok();
	}

}

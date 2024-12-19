package com.ruoyi.project.system.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.notice.service.NoticeService;

/**
 * 公告 信息操作处理
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/notice")
public class NoticeController extends BaseController {
	private String prefix = "system/notice";

	@Autowired
	private NoticeService noticeService;

	@RequiresPermissions("system:notice:view")
	@GetMapping()
	public String notice() {
		return prefix + "/notice";
	}

	/**
	 * 查询公告列表
	 */
	@RequiresPermissions("system:notice:list")
	@PostMapping("/list")
	@ResponseBody
    public TableDataInfo list(HttpServletRequest request) {
        return noticeService.selectNoticeList(request);
    }

	/**
	 * 新增公告
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 修改公告
	 */
	@GetMapping("/edit/{notice_id}")
	public String edit(@PathVariable("notice_id") String notice_id, ModelMap mmap) {
		mmap.put("notice", noticeService.selectNoticeById(notice_id));
		return prefix + "/edit";
	}

	/**
	 * 查看公告
	 */
	@GetMapping("/detail/{notice_id}")
	public String detail(@PathVariable("notice_id") String notice_id, ModelMap mmap) {
		mmap.put("notice", noticeService.selectNoticeById(notice_id));
		return prefix + "/detail";
	}

    /**
     * 保存岗位
     */
    @RequiresPermissions("system:notice:save")
    @Log(title = "通知公告", businessType = BusinessType.SAVE)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult saveOrUpdate(HttpServletRequest request) {
    	return toAjax(noticeService.saveNotice(request));
    }

	/**
	 * 删除公告
	 */
	@RequiresPermissions("system:notice:remove")
	@Log(title = "通知公告", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(noticeService.deleteNoticeByIds(ids));
	}
}
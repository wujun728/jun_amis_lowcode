package com.jun.plugin.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.common.WebUtils;
import com.jun.plugin.system.domain.Notice;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.service.NoticeService;
import com.jun.plugin.system.vo.NoticeVo;

import java.util.Arrays;
import java.util.Date;

/**
 * ClassName: NoticeController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/**
	 * 公告数据
	 *
	 * @param noticeVo
	 * @return
	 */
	@RequestMapping("loadAllNotice")
	public Object loadAllNotice(NoticeVo noticeVo) {
		return this.noticeService.queryAllNotice(noticeVo);
	}

	/**
	 * 添加公告
	 *
	 * @param notice
	 * @return
	 */
	@RequestMapping("addNotice")
	public Object addNotice(Notice notice) {
		try {
			notice.setCreatetime(new Date());
			User user = (User) WebUtils.getThisName();
			notice.setOpername(user.getName());
			this.noticeService.save(notice);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改公告
	 *
	 * @param notice
	 * @return
	 */
	@RequestMapping("updateNotice")
	public Object updateNotice(Notice notice) {
		try {
			this.noticeService.updateById(notice);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除公告
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteNotice")
	public Object deleteNotice(Integer id) {
		try {
			this.noticeService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除公告
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDeleteNotice")
	public Object batchDeleteNotice(Integer[] ids) {
		try {
			this.noticeService.removeByIds(Arrays.asList(ids));
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}
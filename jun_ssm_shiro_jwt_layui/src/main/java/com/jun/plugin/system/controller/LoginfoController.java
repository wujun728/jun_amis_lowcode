package com.jun.plugin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.domain.Loginfo;
import com.jun.plugin.system.service.LoginfoService;
import com.jun.plugin.system.vo.LoginfoVo;

import java.util.Arrays;

/**
 * ClassName: LoginfoController Description: layui date: 2020/4/15 16:23
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/loginfo")
public class LoginfoController {

	@Autowired
	private LoginfoService loginfoService;

	/**
	 * 加载日志数据
	 *
	 * @param loginfoVo
	 * @return
	 */
	@RequestMapping("loadAllLoginfo")
	public Object loadAllLoginfo(LoginfoVo loginfoVo) {
		return this.loginfoService.queryAllLoginfo(loginfoVo);
	}

	/**
	 * 日志删除
	 *
	 * @param id
	 * @return 返回删除状态
	 */
	@RequestMapping("deleteLoginfo")
	public ResultObj deleteLoginfo(Integer id) {
		try {
			this.loginfoService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除日志
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDeleteLoginfo")
	public ResultObj batchDeleteLoginfo(Integer[] ids) {
		try {
			if (null != ids && ids.length > 0) {
				this.loginfoService.removeByIds(Arrays.asList(ids));
				return new ResultObj(200, "nb666提醒您: 删除成功");
			} else {
				return new ResultObj(-1, "nb666提醒您:传入的ID不能为空");
			}
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}
package com.jun.plugin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.service.SystemlogService;
import com.jun.plugin.system.vo.SystemlogVo;

import java.util.Arrays;

/**
 * ClassName: SystemLogController Description: layui date: 2020/4/16 17:57
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/systemLog")
public class SystemLogController {

	@Autowired
	private SystemlogService systemlogService;

	/**
	 * 加载操作日志
	 *
	 * @param systemlogVo
	 * @return
	 */
	@RequestMapping("loadAllSystemLog")
	public Object loadAllSystemLog(SystemlogVo systemlogVo) {
		return this.systemlogService.queryAllSystemLog(systemlogVo);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDeleteSystemLog")
	public Object batchDeleteSystemLog(Integer[] ids) {
		try {
			this.systemlogService.removeByIds(Arrays.asList(ids));
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 单个删除
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping("deleteSystemLog")
	public Object deleteSystemLog(Integer id) {
		try {
			this.systemlogService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}
}
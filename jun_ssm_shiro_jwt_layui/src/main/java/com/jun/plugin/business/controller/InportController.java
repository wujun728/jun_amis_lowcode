package com.jun.plugin.business.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Inport;
import com.jun.plugin.business.service.InportService;
import com.jun.plugin.business.vo.InportVo;
import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.ResultObj;

import java.util.Arrays;
import java.util.Date;

/**
 * ClassName: InportController Description: layui date: 2020/4/16 15:52
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/inport")
public class InportController {

	@Autowired
	private InportService inportService;

	/**
	 * 查询所有商品进货数据
	 *
	 * @param inportVo
	 * @return
	 */
	@RequestMapping("loadAllInport")
	public Object loadAllInport(InportVo inportVo) {
		return this.inportService.queryAllInport(inportVo);
	}

	/**
	 * 添加商品进货
	 *
	 * @param inport
	 * @return
	 */
	@RequestMapping("addInport")
	public Object addInport(Inport inport) {
		try {
			ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
			inport.setOperateperson(activeUser.getUser().getName());
			inport.setInporttime(new Date());
			this.inportService.saveInport(inport);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改商品进货
	 *
	 * @param inport
	 * @return
	 */
	@RequestMapping("updateInport")
	public Object updateInport(Inport inport) {
		try {
			this.inportService.updateInport(inport);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除商品进货
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteInport")
	public Object deleteInport(Integer id) {
		try {
			this.inportService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

}
package com.jun.plugin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.business.domain.Outport;
import com.jun.plugin.business.service.OutportService;
import com.jun.plugin.business.vo.OutportVo;
import com.jun.plugin.system.common.ResultObj;

/**
 * 
 * 
 * ClassName: OutportController create: 2020-04-27 14:37
 *
 * @author: Wujun @since： JDK1.8
 *
 *          退货
 **/

@RestController
@RequestMapping("api/outport")
public class OutportController {

	@Autowired
	private OutportService outportService;

	/**
	 * 查询
	 */
	@RequestMapping("loadAllOutport")
	public Object loadAllOutport(OutportVo outportVo) {
		return this.outportService.queryAllOutport(outportVo);
	}

	/**
	 * 添加退货信息
	 */
	@RequestMapping("addOutport")
	public ResultObj addOutport(Outport outport) {
		try {
			this.outportService.saveOutport(outport);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}

	}

}

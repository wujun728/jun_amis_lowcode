package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Inport;
import com.jun.plugin.business.vo.InportVo;
import com.jun.plugin.system.common.DataGridView;

import java.io.Serializable;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/26
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface InportService extends IService<Inport> {

	/**
	 * 保存进货信息
	 *
	 * @param inportVo
	 * @return
	 */
	DataGridView queryAllInport(InportVo inportVo);

	/**
	 * 保存进货信息
	 *
	 * @param inport
	 * @return
	 */
	Inport saveInport(Inport inport);

	/**
	 * 更新进货信息
	 *
	 * @param inport
	 * @return
	 */
	Inport updateInport(Inport inport);

}

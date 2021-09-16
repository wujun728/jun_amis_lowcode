package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Outport;
import com.jun.plugin.business.vo.OutportVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface OutportService extends IService<Outport> {

	Outport saveOutport(Outport outport);

	DataGridView queryAllOutport(OutportVo outportVo);
}

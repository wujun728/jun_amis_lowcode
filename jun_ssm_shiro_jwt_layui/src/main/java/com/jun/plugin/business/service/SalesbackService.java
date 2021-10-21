package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Salesback;
import com.jun.plugin.business.vo.SalesbackVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/27
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface SalesbackService extends IService<Salesback> {

	DataGridView queryAllSalesback(SalesbackVo salesbackVo);

	Salesback saveSalesback(Salesback salesback);
}

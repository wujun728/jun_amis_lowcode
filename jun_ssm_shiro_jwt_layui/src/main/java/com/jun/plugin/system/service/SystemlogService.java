package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Systemlog;
import com.jun.plugin.system.vo.SystemlogVo;

/**
 * ClassName: SystemlogService Description: layui date: 2020/4/15 18:09
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface SystemlogService extends IService<Systemlog> {

	/**
	 * 查询操作日志
	 * 
	 * @param systemlogVo
	 */
	DataGridView queryAllSystemLog(SystemlogVo systemlogVo);
}

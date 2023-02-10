package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Loginfo;
import com.jun.plugin.system.vo.LoginfoVo;

/**
 * ClassName: LoginfoService Description: layui date: 2020/4/15 19:10
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface LoginfoService extends IService<Loginfo> {

	/**
	 * 查询全部日志
	 *
	 * @param loginfoVo 条件
	 * @return
	 */
	DataGridView queryAllLoginfo(LoginfoVo loginfoVo);

}

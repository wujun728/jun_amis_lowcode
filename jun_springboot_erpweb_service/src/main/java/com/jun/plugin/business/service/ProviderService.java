package com.jun.plugin.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.business.domain.Provider;
import com.jun.plugin.business.vo.ProviderVo;
import com.jun.plugin.system.common.DataGridView;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/25
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface ProviderService extends IService<Provider> {

	/**
	 * 获取所有供应商信息
	 *
	 * @param providerVo
	 * @return
	 */
	DataGridView queryAllProvider(ProviderVo providerVo);

	/**
	 * 添加供应商
	 *
	 * @param provider
	 * @return
	 */
	Provider saveProvider(Provider provider);

	/**
	 * 更新供应商信息
	 *
	 * @param provider
	 * @return
	 */
	Provider updateProvider(Provider provider);

	/**
	 * 查询所有供应商 不分页
	 * 
	 * @return
	 */
	DataGridView getAllAvailableProvider();
}

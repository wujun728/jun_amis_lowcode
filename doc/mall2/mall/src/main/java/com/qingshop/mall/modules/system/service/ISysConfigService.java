package com.qingshop.mall.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysConfig;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

/**
 * ConfigService
 */
public interface ISysConfigService extends IService<SysConfig> {

	String selectAll();

	String selectStorageConfig();

	void saveStorageConfig(ConfigStorageVo vo);

}

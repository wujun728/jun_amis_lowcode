package com.jun.plugin.modules.sys.service;

import com.jun.plugin.modules.sys.vo.ConfigStorageVo;

/**
 * ConfigService
 */
public interface ConfigService {

	String getStorage();

	/**
	 * 读取文件存储配置
	 * 
	 * @return
	 */
	String getStorageConfig();

	/**
	 * 存储缓存
	 * 
	 * @param vo
	 */
	void saveStorageConfig(ConfigStorageVo vo);

}

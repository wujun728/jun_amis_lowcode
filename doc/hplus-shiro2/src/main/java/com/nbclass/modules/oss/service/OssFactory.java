package com.nbclass.modules.oss.service;

import com.alibaba.fastjson.JSON;
import com.nbclass.common.holder.SpringContextHolder;
import com.nbclass.modules.oss.service.impl.AliyunOssService;
import com.nbclass.modules.oss.service.impl.LocalOssService;
import com.nbclass.modules.oss.service.impl.OssService;
import com.nbclass.modules.oss.service.impl.QcloudOssService;
import com.nbclass.modules.oss.service.impl.QiniuOssService;
import com.nbclass.modules.sys.service.ConfigService;
import com.nbclass.modules.sys.vo.ConfigStorageVo;

/**
 * 文件上传
 */
public final class OssFactory {

	public static OssService init() {
		// 获取云存储配置信息
		ConfigService sysConfigService = SpringContextHolder.getBean(ConfigService.class);
		String json = sysConfigService.getStorageConfig();
		ConfigStorageVo config = JSON.parseObject(json, ConfigStorageVo.class);
		if (config.getType() == OssTypeEnum.LOCAL.getValue()) {
			return new LocalOssService(config);
		} else if (config.getType() == OssTypeEnum.QINIU.getValue()) {
			return new QiniuOssService(config);
		} else if (config.getType() == OssTypeEnum.ALIYUN.getValue()) {
			return new AliyunOssService(config);
		} else if (config.getType() == OssTypeEnum.QCLOUD.getValue()) {
			return new QcloudOssService(config);
		}
		return null;
	}

	public static OssService init(Integer type) {
		// 获取云存储配置信息
		ConfigService sysConfigService = SpringContextHolder.getBean(ConfigService.class);
		String json = sysConfigService.getStorageConfig();
		ConfigStorageVo config = JSON.parseObject(json, ConfigStorageVo.class);
		if (type.equals(OssTypeEnum.LOCAL.getValue())) {
			return new LocalOssService(config);
		} else if (type.equals(OssTypeEnum.QINIU.getValue())) {
			return new QiniuOssService(config);
		} else if (type.equals(OssTypeEnum.ALIYUN.getValue())) {
			return new AliyunOssService(config);
		} else if (type.equals(OssTypeEnum.QCLOUD.getValue())) {
			return new QcloudOssService(config);
		}
		return null;
	}

}

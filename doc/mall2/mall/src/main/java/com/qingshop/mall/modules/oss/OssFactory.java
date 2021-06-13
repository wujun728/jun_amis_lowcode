package com.qingshop.mall.modules.oss;

import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.spring.SpringUtils;
import com.qingshop.mall.modules.system.service.ISysConfigService;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

/**
 * 文件上传
 */
public final class OssFactory {

	public static OssService init() {
		// 获取云存储配置信息
		ISysConfigService sysConfigService = SpringUtils.getBean(ISysConfigService.class);
		String json = sysConfigService.selectStorageConfig();
		ConfigStorageVo config = JsonUtils.jsonToBean(json, ConfigStorageVo.class);
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
		ISysConfigService sysConfigService = SpringUtils.getBean(ISysConfigService.class);
		String json = sysConfigService.selectStorageConfig();
		ConfigStorageVo config = JsonUtils.jsonToBean(json, ConfigStorageVo.class);
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

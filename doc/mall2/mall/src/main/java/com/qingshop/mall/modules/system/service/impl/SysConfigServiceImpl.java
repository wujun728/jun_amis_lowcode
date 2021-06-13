package com.qingshop.mall.modules.system.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.constant.Constants;
import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.enums.ConfigKey;
import com.qingshop.mall.modules.system.entity.SysConfig;
import com.qingshop.mall.modules.system.mapper.SysConfigMapper;
import com.qingshop.mall.modules.system.service.ISysConfigService;
import com.qingshop.mall.modules.system.service.RedisService;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

/**
 * ConfigServiceImpl
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

	@Autowired
	private RedisService redisService;

	@Value("${global.redisrun}")
	private boolean redisrun;

	@Override
	public String selectAll() {
		// 未开启redis
		if (!redisrun) {
			String configValueStr = this.baseMapper.getByKey(ConfigKey.CONFIG_STORAGE.getValue());
			if (StringUtils.isEmpty(configValueStr)) {
				ConfigStorageVo configStorageVo = new ConfigStorageVo();
				configStorageVo.setType(Constants.LOCAL_OSS_TYPE);
				return JSON.toJSONString(configStorageVo);
			} else {
				return configValueStr;
			}
		}
		// 开启redis
		String configValueStr = redisService.get(ConfigKey.SYS_CONFIG.getValue());
		if (configValueStr == null || StringUtils.isEmpty(configValueStr)) {
			configValueStr = this.baseMapper.getByKey(ConfigKey.CONFIG_STORAGE.getValue());
			redisService.set(ConfigKey.SYS_CONFIG.getValue(), configValueStr);
		}
		if (configValueStr == null) {
			ConfigStorageVo configStorageVo = new ConfigStorageVo();
			configStorageVo.setType(Constants.LOCAL_OSS_TYPE);
			return JSON.toJSONString(configStorageVo);
		}
		return configValueStr;
	}

	@Override
	public String selectStorageConfig() {
		// 未开启redis
		if (!redisrun) {
			return this.baseMapper.getByKey(ConfigKey.CONFIG_STORAGE.getValue());
		}
		// 开启redis
		boolean hasKey = redisService.hasKey(ConfigKey.SYS_CONFIG.getValue());
		if (hasKey) {
			return redisService.get(ConfigKey.SYS_CONFIG.getValue());
		}
		String configStr = this.baseMapper.getByKey(ConfigKey.CONFIG_STORAGE.getValue());
		redisService.set(ConfigKey.SYS_CONFIG.getValue(), configStr, 30L, TimeUnit.DAYS);
		return configStr;
	}

	@Override
	public void saveStorageConfig(ConfigStorageVo vo) {
		String configStr = this.baseMapper.getByKey(ConfigKey.CONFIG_STORAGE.getValue());
		if (StringUtils.isEmpty(configStr)) {
			SysConfig initSysConfig = new SysConfig();
			initSysConfig.setConfigId(DistributedIdWorker.nextId());
			initSysConfig.setSysKey(ConfigKey.CONFIG_STORAGE.getValue());
			initSysConfig.setSysValue(JSON.toJSONString(vo));
			initSysConfig.setRemark("文件存储配置");
			initSysConfig.setStatus(1);
			initSysConfig.setCreateTime(new Date());
			initSysConfig.setUpdateTime(new Date());
			this.baseMapper.insert(initSysConfig);
		} else {
			this.baseMapper.updateByKey(ConfigKey.CONFIG_STORAGE.getValue(), JsonUtils.beanToJson(vo));
		}
		// 开启redis
		if (redisrun) {
			redisService.del(ConfigKey.SYS_CONFIG.getValue());
		}
	}

}

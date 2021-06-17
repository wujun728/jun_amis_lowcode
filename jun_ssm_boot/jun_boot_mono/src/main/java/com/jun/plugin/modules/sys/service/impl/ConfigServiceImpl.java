package com.jun.plugin.modules.sys.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.enums.ConfigKey;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.modules.sys.mapper.ConfigMapper;
import com.jun.plugin.modules.sys.model.SysConfig;
import com.jun.plugin.modules.sys.service.ConfigService;
import com.jun.plugin.modules.sys.service.RedisService;
import com.jun.plugin.modules.sys.vo.ConfigStorageVo;

import tk.mybatis.mapper.entity.Example;

/**
 * ConfigServiceImpl
 */
@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private RedisService redisService;

	@Autowired
	private ConfigMapper configMapper;

	@Value("${global.redisrun}")
	private boolean redisrun;

	@Override
	public String getStorage() {
		// 未开启redis
		if (!redisrun) {
			Example example = new Example(SysConfig.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
			SysConfig sysConfig = configMapper.selectOneByExample(example);
			if (sysConfig.getSysValue() == null) {
				ConfigStorageVo configStorageVo = new ConfigStorageVo();
				configStorageVo.setType(CoreConst.LOCAL_OSS_TYPE);
				return JSON.toJSONString(configStorageVo);
			} else {
				return sysConfig.getSysValue();
			}
		}

		// 开启redis
		Object sysValue = redisService.get(ConfigKey.SYS_CONFIG.getValue());
		if (sysValue == null || StringUtils.isEmpty(sysValue.toString())) {
			Example example = new Example(SysConfig.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
			SysConfig sysConfig = configMapper.selectOneByExample(example);
			if (sysConfig != null) {
				sysValue = sysConfig.getSysValue();
				redisService.set(ConfigKey.SYS_CONFIG.getValue(), sysConfig.getSysValue());
			}
		}
		if (sysValue == null) {
			ConfigStorageVo configStorageVo = new ConfigStorageVo();
			configStorageVo.setType(CoreConst.LOCAL_OSS_TYPE);
			return JSON.toJSONString(configStorageVo);
		}
		return sysValue.toString();
	}

	@Override
	public String getStorageConfig() {
		// 未开启redis
		if (!redisrun) {
			Example example = new Example(SysConfig.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
			SysConfig sysConfig = configMapper.selectOneByExample(example);
			return sysConfig.getSysValue();
		}

		// 开启redis
		boolean hasKey = redisService.hasKey(ConfigKey.SYS_CONFIG.getValue());
		if (hasKey) {
			return redisService.get(ConfigKey.SYS_CONFIG.getValue());
		}
		Example example = new Example(SysConfig.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
		SysConfig sysConfig = configMapper.selectOneByExample(example);
		redisService.set(ConfigKey.SYS_CONFIG.getValue(), sysConfig.getSysValue(), 30L, TimeUnit.DAYS);
		return sysConfig.getSysValue();
	}

	@Override
	public void saveStorageConfig(ConfigStorageVo vo) {
		Example example = new Example(SysConfig.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
		SysConfig sysConfig = configMapper.selectOneByExample(example);
		if (sysConfig == null) {
			SysConfig initSysConfig = new SysConfig();
			initSysConfig.setId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
			initSysConfig.setSysKey(ConfigKey.CONFIG_STORAGE.getValue());
			initSysConfig.setSysValue(JSON.toJSONString(vo));
			initSysConfig.setRemark("文件存储配置");
			initSysConfig.setStatus(1);
			initSysConfig.setCreateTime(new Date());
			initSysConfig.setUpdateTime(new Date());
			configMapper.insertSelective(initSysConfig);
		} else {
			SysConfig initSysConfig = new SysConfig();
			initSysConfig.setSysValue(JSON.toJSONString(vo));
			initSysConfig.setUpdateTime(new Date());
			Example updateExample = new Example(SysConfig.class);
			Example.Criteria updateCriteria = updateExample.createCriteria();
			updateCriteria.andEqualTo("sysKey", ConfigKey.CONFIG_STORAGE.getValue());
			configMapper.updateByExampleSelective(initSysConfig, updateExample);
		}
		if (redisrun) {
			redisService.del(ConfigKey.SYS_CONFIG.getValue());
		}
	}
}

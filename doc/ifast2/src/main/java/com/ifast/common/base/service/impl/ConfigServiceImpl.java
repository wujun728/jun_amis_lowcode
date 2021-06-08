package com.ifast.common.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifast.common.base.CoreServiceImpl;
import com.ifast.common.base.dao.ConfigDao;
import com.ifast.common.base.domain.ConfigDO;
import com.ifast.common.base.service.ConfigService;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年4月6日 | Aron</small>
 */
@Service
public class ConfigServiceImpl extends CoreServiceImpl<ConfigDao, ConfigDO> implements ConfigService {

	@Override
	public ConfigDO getByKey(String k) {
		ConfigDO entity = new ConfigDO();
		entity.setK(k);
		return baseMapper.selectOne(new QueryWrapper<ConfigDO>(entity));
	}

	@Override
	public String getValueByKey(String k) {
		ConfigDO bean = this.getByKey(k);
		return bean == null ? "" : bean.getV();
	}

	@Override
	public void updateKV(Map<String, String> kv) {
		for (Map.Entry<String, String> entry : kv.entrySet()) {
			ConfigDO config = this.getByKey(entry.getKey());
			config.setV(entry.getValue());
			super.updateById(config);
		}
	}

	@Override
	public List<ConfigDO> findListByKvType(int kvType) {
		ConfigDO configDO = new ConfigDO();
		configDO.setKvType(kvType);
		Wrapper<ConfigDO> ew   = new QueryWrapper<>(configDO);
		List<ConfigDO>    list = super.list(ew);
		return list;
	}

}

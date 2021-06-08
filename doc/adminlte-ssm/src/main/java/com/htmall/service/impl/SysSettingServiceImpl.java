package com.htmall.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htmall.entity.SysSetting;
import com.htmall.mapper.SysSettingMapper;
import com.htmall.service.ISysSettingService;

/**
 *
 * SysSetting 表数据服务层接口实现类
 *
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {

	@Cacheable(value = "settingCache")
	@Override
	public List<SysSetting> findAll() {
		return this.list(new QueryWrapper<SysSetting>().orderByAsc("sort"));
	}

}
package com.jun.plugin.api.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jun.plugin.api.entity.SysSetting;
import com.jun.plugin.api.mapper.SysSettingMapper;
import com.jun.plugin.api.service.ISysSettingService;

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
		// TODO Auto-generated method stub
		return this.selectList(new EntityWrapper<SysSetting>().orderBy("sort",true));
	}


}
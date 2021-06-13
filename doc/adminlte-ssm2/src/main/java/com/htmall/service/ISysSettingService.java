package com.htmall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htmall.entity.SysSetting;

/**
 *
 * SysSetting 表数据服务层接口
 *
 */
public interface ISysSettingService extends IService<SysSetting> {

	List<SysSetting> findAll();

}
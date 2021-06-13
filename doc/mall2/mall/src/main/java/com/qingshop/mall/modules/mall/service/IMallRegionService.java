package com.qingshop.mall.modules.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.mall.entity.MallRegion;

/**
 * 省市区信息表 服务类
 */
public interface IMallRegionService extends IService<MallRegion> {

	List<MallRegion> listCommonArea();

}

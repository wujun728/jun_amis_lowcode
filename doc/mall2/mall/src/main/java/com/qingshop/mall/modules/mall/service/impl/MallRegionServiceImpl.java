package com.qingshop.mall.modules.mall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallRegion;
import com.qingshop.mall.modules.mall.mapper.MallRegionMapper;
import com.qingshop.mall.modules.mall.service.IMallRegionService;

/**
 * 省市区信息表 服务实现类
 */
@Service
public class MallRegionServiceImpl extends ServiceImpl<MallRegionMapper, MallRegion> implements IMallRegionService {

	@Override
	public List<MallRegion> listCommonArea() {
		return this.list(new QueryWrapper<MallRegion>().lt("level", 3));
	}

}
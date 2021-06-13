package com.qingshop.mall.modules.mall.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingshop.mall.modules.mall.entity.MallCategory;

/**
 * 类目表 Mapper 接口
 */
public interface MallCategoryMapper extends BaseMapper<MallCategory> {

	List<MallCategory> selectCategoryList(MallCategory mallCategory);

	List<Long> selectCategoryIdByPid(String parentId);

}

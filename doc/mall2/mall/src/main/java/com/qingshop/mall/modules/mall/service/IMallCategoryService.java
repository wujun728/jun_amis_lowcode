package com.qingshop.mall.modules.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.common.bean.Ztree;
import com.qingshop.mall.modules.mall.entity.MallCategory;

/**
 * 类目表 服务类
 */
public interface IMallCategoryService extends IService<MallCategory> {

	void insertMallCategory(MallCategory mallCategory);

	List<Ztree> selectCategoryTree(MallCategory mallCategory);

	List<Long> selectCategoryIdByPid(String parentId);

	List<MallCategory> getChildPerms(List<MallCategory> categoryList, int parentId);

}

package com.jun.biz.manager.service;

import java.util.List;
import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.category.CreateCategoryDTO;
import com.jun.biz.manager.dto.category.ModifyCategoryDTO;
import com.jun.biz.manager.vo.category.CategoryVO;


public interface CategoryService {


	ResultVO<Boolean> create(CreateCategoryDTO dto);

	ResultVO<Boolean> modify(ModifyCategoryDTO dto);

	ResultVO<Boolean> delete(Set<Long> ids);

	ResultVO<List<CategoryVO>> subCategory(Long pid);
}

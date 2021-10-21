package com.jun.biz.manager.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.category.CreateCategoryDTO;
import com.jun.biz.manager.dto.category.ModifyCategoryDTO;
import com.jun.biz.manager.service.CategoryService;
import com.jun.biz.manager.vo.category.CategoryVO;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Resource
	private CategoryService categoryService;



	@PerCode("category:add")
	@PostMapping("create")
	public ResultVO<Boolean> create(@Validated @RequestBody CreateCategoryDTO dto) {
		return categoryService.create(dto);
	}

	@PerCode("category:update")
	@PostMapping("modify")
	public ResultVO<Boolean> modify(@Validated @RequestBody ModifyCategoryDTO dto) {
		return categoryService.modify(dto);
	}

	@PerCode("category:del")
	@DeleteMapping("delete")
	public ResultVO<Boolean> delete(@RequestBody @Validated @NotNull @Size(min = 1) Set<Long> ids) {
		return categoryService.delete(ids);
	}

	@PerCode({"category:mgt", "product:add", "product:modify"})
	@GetMapping("sub-category")
	public ResultVO<List<CategoryVO>> subCategory(Long pid) {
		return categoryService.subCategory(pid);
	}


}

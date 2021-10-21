
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.dto.category.CreateCategoryDTO;
import com.jun.biz.manager.dto.category.ModifyCategoryDTO;
import com.jun.biz.manager.model.Category;
import com.jun.biz.manager.vo.category.CategoryVO;

import java.util.List;
import java.util.Set;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConverter {

    List<CategoryVO> toVo(List<Category> entityList);

    Set<CategoryVO> toVo(Set<Category> entityList);

    Category createDtoToEntity(CreateCategoryDTO dto);

    Category modifyDtoToEntity(ModifyCategoryDTO dto);
}

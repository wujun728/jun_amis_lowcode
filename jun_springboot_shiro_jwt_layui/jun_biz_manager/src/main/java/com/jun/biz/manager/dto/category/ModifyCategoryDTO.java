package com.jun.biz.manager.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyCategoryDTO extends CreateCategoryDTO {


    @NotNull
    private Long id;


}

package com.jun.biz.manager.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyProductDTO extends CreateProductDTO {


    @NotNull
    private Long id;


}

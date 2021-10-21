package com.jun.biz.manager.dto.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyOrderDTO extends CreateOrderDTO {


    @NotNull
    private Long id;


}

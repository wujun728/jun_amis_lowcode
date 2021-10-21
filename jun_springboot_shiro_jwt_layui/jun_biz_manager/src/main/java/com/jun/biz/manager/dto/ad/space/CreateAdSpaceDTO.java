package com.jun.biz.manager.dto.ad.space;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAdSpaceDTO {

    @NotBlank

    private String name;
    @NotBlank
    private String no;
    @NotBlank
    private String template;
    @NotNull
    private Integer number;
    @NotNull
    private Integer type;
    @NotNull
    private Integer width;
    @NotNull
    private Integer height;


}

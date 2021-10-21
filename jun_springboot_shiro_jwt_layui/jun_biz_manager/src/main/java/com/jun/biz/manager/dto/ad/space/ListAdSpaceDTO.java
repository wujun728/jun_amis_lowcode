package com.jun.biz.manager.dto.ad.space;

import com.jun.biz.common.base.dto.PageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListAdSpaceDTO extends PageDTO {


    private Integer id;

    private String name;

    private String no;

    private String template;

    private Integer number;

    private Integer type;

    private Integer width;

    private Integer height;


}

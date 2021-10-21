package com.jun.biz.manager.vo.ad.space;


import lombok.Data;

import java.util.List;

import com.jun.biz.manager.vo.ad.item.AdItemVO;


/**
 *
 */

@Data
public class AdSpaceVO {


    /**
     * id       db_column: id
     */
    private Long id;
    /**
     * 广告名称       db_column: name
     */
    private String name;
    /**
     * 广告编号       db_column: no
     */
    private String no;
    /**
     * 模板名称       db_column: template_name
     */
    private String template;
    /**
     * 容量       db_column: number
     */
    private Integer number;
    /**
     * 类型       db_column: type
     */
    private Integer type;
    /**
     * 图片       db_column: width
     */
    private Integer width;
    /**
     * height       db_column: height
     */
    private Integer height;

    private List<AdItemVO> adItemVos;

    private String domain;
}







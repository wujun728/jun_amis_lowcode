package com.ifast.common.tags.vo;

import lombok.Data;

/**
 * 控件数值vo
 *
 * @author: zet
 * @date:2018/8/22
 */
@Data
public class ValueVO {
    /**
     * value值
     */
    private String value;

    /**
     * 显示名称
     */
    private String name;

    /**
     * 是否选中
     */
    private Boolean selected;
}

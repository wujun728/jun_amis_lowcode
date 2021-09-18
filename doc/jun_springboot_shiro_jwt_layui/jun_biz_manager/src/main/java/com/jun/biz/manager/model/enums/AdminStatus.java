package com.jun.biz.manager.model.enums;

import lombok.Getter;

/**
 * Created on 2020/10/14 19:36
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Getter
public enum AdminStatus {
    /**
     *
     */
    NORMAL(1, "正常"), FROZEN(2, "冻结");
    private final Integer code;
    private final String desc;


    AdminStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}

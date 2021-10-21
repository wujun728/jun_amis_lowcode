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
public enum ProductStatus {
    /**
     *
     */
    SELLING(1, "出售中"), SUSPEND(2, "暂停"), SOLD_OUT(3, "售罄");
    private final Integer code;
    private final String desc;


    ProductStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}

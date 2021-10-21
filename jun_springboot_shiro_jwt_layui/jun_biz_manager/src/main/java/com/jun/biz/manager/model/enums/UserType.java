package com.jun.biz.manager.model.enums;

import lombok.Getter;

/**
 * Created on 2020/10/27 17:29
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Getter
public enum UserType {
    /**
     *
     */
    LOCAL(1, "本站"), WEIXIN(2, "微信"), QQ(3, "QQ");
    private final Integer code;
    private final String desc;


    UserType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

package com.jun.biz.common.runtime;

import lombok.Data;

/**
 * Created on 2020/10/23 11:27
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class AdminSessionObject {
    private Long adminId;
    private String username;
    private String realName;
}

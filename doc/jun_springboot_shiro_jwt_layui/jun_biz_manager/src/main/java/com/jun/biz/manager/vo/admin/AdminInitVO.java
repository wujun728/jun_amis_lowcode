package com.jun.biz.manager.vo.admin;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Created on 2020/10/14 17:57
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Data
public class AdminInitVO {
    private List<PermissionVO> menuInfo;
    private Set<String> perCodes;
    private String realName;
    private String frontDomain;
}

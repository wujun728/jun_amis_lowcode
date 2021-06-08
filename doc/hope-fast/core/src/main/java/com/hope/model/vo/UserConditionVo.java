package com.hope.model.vo;

import com.hope.model.bean.SysUserBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aodeng
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserConditionVo {

    private SysUserBean user;
}

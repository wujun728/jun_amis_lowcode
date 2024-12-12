package com.jqp.admin.rbac.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-16 10:15:42
 * @remark 企业用户
 */
@Data
public class EnterpriseUser extends BaseData {
    //企业id
    private Long enterpriseId;
    //用户id
    private Long userId;
    //部门id
    private Long deptId;
}
package com.jun.biz.manager.model;


import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

/**
 *
 */

@Data
public class AdminRole {


    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * adminId       db_column: admin_id
     */

    private Long adminId;

    /**
     * roleId       db_column: role_id
     */

    private Long roleId;


    private String roleName;


}







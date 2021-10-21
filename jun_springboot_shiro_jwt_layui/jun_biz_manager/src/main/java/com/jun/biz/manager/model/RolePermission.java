package com.jun.biz.manager.model;


import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

/**
 * 
 */

@Data
public class RolePermission  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * roleId       db_column: role_id
     */

private Long roleId;

    /**
     * permissionId       db_column: permission_id
     */

private Long permissionId;







}







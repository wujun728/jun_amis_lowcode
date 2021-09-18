package com.jun.biz.manager.service;

import java.util.List;
import java.util.Set;

import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.permission.CreatePermissionDTO;
import com.jun.biz.manager.dto.permission.ModifyPermissionDTO;
import com.jun.biz.manager.model.Permission;
import com.jun.biz.manager.vo.admin.PermissionVO;

/**
 * Created on 2020/10/14 18:11
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
public interface PermissionService {
    /**
     * 当前用户权限
     * @return
     */
    List<Permission> currentPermissions();

    /**
     * 当前登录人菜单树
     *
     * @return
     */
    ResultVO<List<PermissionVO>> menuTree();

    /**
     * 所有权限树
     *
     * @return
     */
    ResultVO<List<PermissionVO>> permissionTree();

    ResultVO<List<PermissionVO>> list();

    ResultVO<Boolean> create(CreatePermissionDTO dto);


    ResultVO<Boolean> modify(ModifyPermissionDTO dto);

    ResultVO<Boolean> delete(Set<Long> ids) ;
}

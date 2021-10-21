package com.jun.biz.manager.dao;
import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.RolePermission;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "s_role_permission")
public interface RolePermissionDao extends BaseDao<RolePermission,Long> {
    void deleteByRole(Long roleId);
}

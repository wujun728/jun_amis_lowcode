package com.jun.biz.manager.dao;
import java.util.List;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Permission;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "s_permission")
public interface PermissionDao extends BaseDao<Permission,Long> {
    /**
     *
     * @param adminId
     * @return
     */
    List<Permission> selectByAdminId(Long adminId);
}

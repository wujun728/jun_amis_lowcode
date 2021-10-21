package com.jun.biz.manager.dao;
import java.util.List;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.Role;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "s_role")
public interface RoleDao extends BaseDao<Role,Long> {
    List<Long> selectPermissionIds(Long id);
}

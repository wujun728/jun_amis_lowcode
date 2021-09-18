package com.jun.biz.manager.dao;

import java.util.List;

import com.jun.biz.common.mybatis.provider.BaseDao;
import com.jun.biz.manager.model.AdminRole;
import com.jun.biz.manager.model.annotation.MapperMapping;


@MapperMapping(table = "s_admin_role")
public interface AdminRoleDao extends BaseDao<AdminRole, Long> {

    void deleteByAdmin(Long adminId);

    List<AdminRole> selectRoleByAdmin(List<Long> adminIds);
}

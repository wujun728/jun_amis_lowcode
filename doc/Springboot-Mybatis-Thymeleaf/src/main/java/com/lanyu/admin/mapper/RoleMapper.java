package com.lanyu.admin.mapper;


import com.lanyu.common.model.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    List<Role> selectRoleList(HashMap<String, Object> param);
    int insert(HashMap<String, Object> role);
    int update(HashMap<String, Object> role);
    int deleteRole(String rcode);
    int deleteMdoules(String rcode);
    int deleteUserRole(Integer uid);
    void addUserRoles(List<HashMap> rolmods);
    void addModules(List<HashMap> modules);
    List<Map> getMultselectRoles(Integer uid);
}
package com.lanyu.admin.service;

import com.lanyu.common.model.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface RoleService {
    List<Role> selectRoleList(HashMap<String, Object> param);
    int insert(HashMap<String, Object> param);
    int update(HashMap<String, Object> role);
    int deleteRole(String rcode);
    void editUserRole(List<HashMap> roles,Integer uid);
    void editModules(List<HashMap> rolmods,String rcode);
    List<Map> getMultselectRoles(Integer uid);
}

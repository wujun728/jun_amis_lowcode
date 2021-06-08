package com.lanyu.admin.service.impl;

import com.lanyu.admin.mapper.RoleMapper;
import com.lanyu.admin.mapper.UserMapper;
import com.lanyu.admin.service.RoleService;
import com.lanyu.common.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "roleService")
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper dao;
    @Autowired
    private UserMapper userdao;

    @Override
    public List<Role> selectRoleList(HashMap<String, Object> param) {
        return dao.selectRoleList(param);
    }

    @Override
    public int insert(HashMap<String, Object> param) {
        return dao.insert(param);
    }

    @Override
    public int update(HashMap<String, Object> role) {
        return dao.update(role);
    }

    /**
     * 删除角色，需要删除角色关联用户表和角色关联权限表
     */
    @Override
    public int deleteRole(String rcode) {
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("rcode",rcode);
        userdao.deleteRoles(param);
        dao.deleteMdoules(rcode);
        return dao.deleteRole(rcode);
    }

    /**
     * 删除用户角色，再批量插入
     */
    @Override
    public void editUserRole(List<HashMap> roles,Integer uid) {
         dao.deleteUserRole(uid);
         if(roles.size()>0){
             dao.addUserRoles(roles);
         }
    }

    @Override
    public void editModules(List<HashMap> rolmods,String rcode) {
        dao.deleteMdoules(rcode);
        if(rolmods.size()>0){
            dao.addModules(rolmods);
        }
    }
    @Override
    public List<Map> getMultselectRoles(Integer uid){
        return dao.getMultselectRoles(uid);
    }
}

package com.element.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.utils.MapUtils;
import com.element.modules.sys.mapper.SysUserRoleMapper;
import com.element.modules.sys.entity.SysUserRoleEntity;
import com.element.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户与角色对应关系
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        this.removeByMap(new MapUtils().put("user_id", userId));
        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }
        //保存用户与角色关系
        for (Long roleId : roleIdList) {
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);
            this.save(sysUserRoleEntity);
        }
    }

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        return baseMapper.listRoleIdByUserId(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}

package com.element.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.exception.CustomException;
import com.element.common.utils.Constant;
import com.element.common.utils.PageUtils;
import com.element.common.utils.Query;
import com.element.modules.sys.entity.SysRoleEntity;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.mapper.SysRoleMapper;
import com.element.modules.sys.service.SysRoleMenuService;
import com.element.modules.sys.service.SysRoleService;
import com.element.modules.sys.service.SysUserRoleService;
import com.element.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils listRoleByPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        Long createUserId = (Long) params.get("createUserId");
        IPage<SysRoleEntity> page = this.page(
                new Query<SysRoleEntity>().getPage(params),
                new QueryWrapper<SysRoleEntity>()
                        .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                        .eq(createUserId != null, "create_user_id", createUserId)
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);
        //检查权限是否越权
        checkPrems(role);
        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);
        //检查权限是否越权
        checkPrems(role);
        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));
        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);
        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> listRoleIdByCreatedUserId(Long createUserId) {
        return baseMapper.listRoleIdByCreatedUserId(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRoleEntity role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }
        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.listMenuIdByCreateUserId(role.getCreateUserId());
        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new CustomException("新增角色的权限，已超出你的权限范围");
        }
    }

    @Override
    public Set<String> listRolePermission(SysUserEntity user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            roles.add("admin");
        } else {
            List<SysRoleEntity> perms = baseMapper.listRolePermissionByUserId(user.getUserId());
            Set<String> permsSet = new HashSet<>();
            for (SysRoleEntity perm : perms) {
                permsSet.addAll(Arrays.asList(perm.getRoleName().trim().split(",")));
            }
            roles.addAll(permsSet);
        }
        return roles;
    }
}

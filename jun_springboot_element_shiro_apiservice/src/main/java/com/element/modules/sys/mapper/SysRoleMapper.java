package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> listRoleIdByCreatedUserId(Long createUserId);

    /**
     * 查询用户拥有的角色
     */
    List<SysRoleEntity> listRolePermissionByUserId(Long userId);

}

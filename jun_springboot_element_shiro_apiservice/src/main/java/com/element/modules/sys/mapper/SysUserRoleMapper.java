package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> listRoleIdByUserId(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

}

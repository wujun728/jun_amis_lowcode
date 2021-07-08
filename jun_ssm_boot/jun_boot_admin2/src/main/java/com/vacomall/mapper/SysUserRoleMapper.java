package com.jun.plugin.api.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jun.plugin.api.entity.SysUserRole;

/**
 *
 * SysUserRole 表数据库控制层接口
 *
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	List<String> selectPermissionByUid(String uid);

}
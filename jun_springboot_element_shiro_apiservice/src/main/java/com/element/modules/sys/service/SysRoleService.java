package com.element.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.element.common.utils.PageUtils;
import com.element.modules.sys.entity.SysRoleEntity;
import com.element.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 角色
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils listRoleByPage(Map<String, Object> params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	List<Long> listRoleIdByCreatedUserId(Long createUserId);

    Set<String> listRolePermission(SysUserEntity user);
}

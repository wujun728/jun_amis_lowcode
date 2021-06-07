package com.zhonghe.active4j.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysUserRoleDao;
import com.zhonghe.active4j.system.entity.SysUserRoleEntity;
import com.zhonghe.active4j.system.service.SysUserRoleService;


/**
 * 用户角色管理service类
 * @author teli_
 *
 */
@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

	
}

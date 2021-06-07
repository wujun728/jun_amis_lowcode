package com.zhonghe.active4j.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysRoleMenuDao;
import com.zhonghe.active4j.system.entity.SysRoleMenuEntity;
import com.zhonghe.active4j.system.service.SysRoleMenuService;


/**
 *  角色 菜单service类
 * @author teli_
 *
 */
@Service("sysRoleMenuService")
@Transactional
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

	

}

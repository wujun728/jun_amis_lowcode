package com.qingshop.mall.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.entity.SysUserRole;
import com.qingshop.mall.modules.system.mapper.SysUserMapper;
import com.qingshop.mall.modules.system.service.ISysUserRoleService;
import com.qingshop.mall.modules.system.service.ISysUserService;

/**
 *
 * SysUser 表数据服务层接口实现类
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private ISysUserRoleService sysUserRoleService;

	@Override
	public void insertUser(SysUser user, Long[] roleIds) {
		user.setCreateTime(new Date());
		user.setPassword(ShiroUtils.md51024Pwd(user.getPassword(), user.getUserName()));
		// 保存用户
		this.save(user);
		// 绑定角色
		if (ArrayUtils.isNotEmpty(roleIds)) {
			List<SysUserRole> list = new ArrayList<SysUserRole>();
			for (Long rid : roleIds) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserRoleId(DistributedIdWorker.nextId());
				sysUserRole.setUserId(user.getUserId());
				sysUserRole.setRoleId(rid);
				list.add(sysUserRole);
			}
			sysUserRoleService.saveBatch(list);
		}
	}

	@Override
	public void updateUser(SysUser sysUser, Long[] roleIds) {
		// 更新用户
		sysUser.setPassword(ShiroUtils.md51024Pwd(sysUser.getPassword(), sysUser.getUserName()));
		this.updateById(sysUser);
		// 删除已有权限
		sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
		// 重新绑定角色
		if (ArrayUtils.isNotEmpty(roleIds)) {
			List<SysUserRole> list = new ArrayList<SysUserRole>();
			for (Long rid : roleIds) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserRoleId(DistributedIdWorker.nextId());
				sysUserRole.setUserId(sysUser.getUserId());
				sysUserRole.setRoleId(rid);
				list.add(sysUserRole);
			}
			sysUserRoleService.saveBatch(list);
		}
	}

	@Override
	public Page<Map<Object, Object>> selectUserPage(Page<Map<Object, Object>> page, String search) {
		page.setRecords(baseMapper.selectUserList(page, search));
		return page;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.removeById(id);
		// 删除所关联的角色关系
		sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", id));
	}

	@Override
	public List<String> queryAllPermsByUserId(Long userId) {
		return baseMapper.queryAllPermsByUserId(userId);
	}

}
package com.qingshop.mall.modules.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.system.entity.SysUserRole;
import com.qingshop.mall.modules.system.mapper.SysUserRoleMapper;
import com.qingshop.mall.modules.system.service.ISysUserRoleService;

/**
 * SysUserRole 表数据服务层接口实现类
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

	@Override
	public Set<String> findRolesByUid(Long uid) {
		List<SysUserRole> list = this.list(new QueryWrapper<SysUserRole>().eq("user_id", uid));
		Set<String> set = new HashSet<String>();
		for (SysUserRole ur : list) {
			set.add(ur.getRoleId().toString());
		}
		return set;
	}
}
package com.qingshop.mall.modules.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.system.entity.SysRoleMenu;
import com.qingshop.mall.modules.system.mapper.SysRoleMenuMapper;
import com.qingshop.mall.modules.system.service.ISysRoleMenuService;

/**
 * SysRoleMenu 表数据服务层接口实现类
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

	@Override
	public void addAuth(Long roleId, Long[] menuIds) {
		/**
		 * 删除原有权限
		 */
		this.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
		/**
		 * 重新授权
		 */
		if (ArrayUtils.isNotEmpty(menuIds)) {
			for (Long menuId : menuIds) {
				SysRoleMenu sysRoleMenu2 = new SysRoleMenu();
				sysRoleMenu2.setRoleMenuId(DistributedIdWorker.nextId());
				sysRoleMenu2.setRoleId(roleId);
				sysRoleMenu2.setMenuId(menuId);
				this.save(sysRoleMenu2);
			}
		}
	}

	@Override
	public List<Long> selectRoleMenuIdsByUserId(Long uid) {
		return baseMapper.selectRoleMenuIdsByUserId(uid);
	}

}
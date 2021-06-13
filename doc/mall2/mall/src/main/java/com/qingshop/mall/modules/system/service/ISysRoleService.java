package com.qingshop.mall.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysRole;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface ISysRoleService extends IService<SysRole> {

	void delete(Long id);

}
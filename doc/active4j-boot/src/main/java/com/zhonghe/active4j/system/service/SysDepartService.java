package com.zhonghe.active4j.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;

public interface SysDepartService extends IService<SysDepartEntity> {
	

	/**
	 * 获取指定部门下的用户
	 * @param depart
	 * @return
	 */
	public List<SysUserEntity> getUsersByDept(SysDepartEntity depart);
	
	
	
	/**
	 * 获取指定部门下的子部门
	 * @param depart
	 * @return
	 */
	public List<SysDepartEntity> getChildDepartsByDept(SysDepartEntity depart);

}

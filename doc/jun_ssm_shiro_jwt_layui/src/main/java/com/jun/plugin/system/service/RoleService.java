package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Role;
import com.jun.plugin.system.vo.RoleVo;

import java.util.List;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/19
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface RoleService extends IService<Role> {

	/**
	 * 查询角色数据
	 * 
	 * @param roleVo
	 * @return
	 */
	Object queryAllRole(RoleVo roleVo);

	/**
	 * 保存角色数据
	 * 
	 * @param role
	 */
	Role saveRole(Role role);

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 */
	Role updateRole(Role role);

	/**
	 * 根据角色ID查询当前角色已拥有的菜单id集合
	 * 
	 * @param id
	 * @return
	 */
	List<Integer> queryMenuIdsByRid(Integer id);

	/**
	 * 保存角色和菜单权限 关系
	 * 
	 * @param rid
	 * @param mids
	 */
	void saveRoleMenu(Integer rid, Integer[] mids);

	/**
	 * 查询可用的角色
	 * 
	 * @param roleVo
	 * @return
	 */
	DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo);

	/**
	 * 根据用户ID 查询拥有的角色名称
	 * 
	 * @param id
	 * @return
	 */
	List<String> queryRoleNamesByUid(Integer id);

}

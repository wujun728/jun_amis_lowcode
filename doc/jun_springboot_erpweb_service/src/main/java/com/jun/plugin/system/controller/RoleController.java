package com.jun.plugin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.common.ResultObj;
import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.domain.Role;
import com.jun.plugin.system.service.RoleService;
import com.jun.plugin.system.vo.RoleVo;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * ClassName: RoleController create: 2020-04-17 19:07
 *
 * @author: Wujun @since： JDK1.8
 **/
@RestController
@RequestMapping("api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	/**
	 * 加载角色数据
	 *
	 * @param roleVo
	 * @return
	 */
	@GetMapping("loadAllRole")
	public Object loadAllRole(RoleVo roleVo) {
		return this.roleService.queryAllRole(roleVo);
	}

	/**
	 * 查询所有可用角色
	 * 
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("loadAllAvailableRoleNoPage")
	public Object loadAllAvailableRoleNoPage(RoleVo roleVo) {
		roleVo.setAvailable(Constant.AVAILABLE_TRUE);
		return this.roleService.queryAllAvailableRoleNoPage(roleVo);
	}

	/**
	 * 添加角色
	 *
	 * @param role
	 * @return
	 */
	@PostMapping("addRole")
	public ResultObj addRole(Role role) {
		try {
			// 设置添加时间
			role.setCreatetime(new Date());
			role.setAvailable(Constant.AVAILABLE_TRUE);
			this.roleService.saveRole(role);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改
	 *
	 * @param role
	 * @return
	 */
	@PostMapping("updateRole")
	public ResultObj updateRole(Role role) {
		try {
			this.roleService.updateRole(role);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("deleteRole")
	public ResultObj deleteRole(Integer id) {
		try {
			this.roleService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 根据角色ID查询当前角色已拥有的菜单id集合
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("queryMenuIdsByRid")
	public Object queryMenuIdsByRid(Integer id) {
		List<Integer> menus = this.roleService.queryMenuIdsByRid(id);
		return new DataGridView(menus);
	}

	/**
	 * 保存角色菜单 关系
	 * 
	 * @param rid
	 * @param mids
	 * @return
	 */
	@PostMapping("saveRoleMenu")
	public ResultObj saveRoleMenu(Integer rid, Integer[] mids) {
		try {
			this.roleService.saveRoleMenu(rid, mids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DISPATCH_ERROR;
		}
	}
}
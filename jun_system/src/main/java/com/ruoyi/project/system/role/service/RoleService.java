package com.ruoyi.project.system.role.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.role.domain.Role;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 角色 业务层处理
 * @author ruoyi
 */
@Service
public class RoleService extends CommonService {

    /**
     * 查询角色列表
     * @param request    HttpServletRequest对象
     * @param pagination 是否分页
     * @return 表格分页数据对象
     */
    public TableDataInfo selectRoleList(HttpServletRequest request, boolean pagination) {
    	String role_name = RequestUtil.getValue(request, "role_name");
    	String role_key = RequestUtil.getValue(request, "role_key");
    	String data_scope = RequestUtil.getValue(request, "data_scope");
    	String status = RequestUtil.getValue(request, "status");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, r.status, r.del_flag, "+
    			   "	r.create_time, r.remark, if(r.status=0,'正常','停用') status_name, " +
    			   "	(case r.data_scope when 1 then '所有数据权限' when 2 then '自定义数据权限' when 3 then '本部门数据权限' " +
    			   "	when 4 then '本部门及以下数据权限' when 5 then '仅本人数据权限' else '' end) data_scope_name " +
				   "  from sys_role r where r.del_flag = '0' ");

    	if(StrUtil.isNotBlank(role_name)) {
    		sql.append(" and r.role_name like concat('%', ?, '%') ");
    		paramList.add(role_name);
    	}
    	if(StrUtil.isNotBlank(role_key)) {
    		sql.append(" and r.role_key like concat('%', ?, '%') ");
    		paramList.add(role_key);
    	}
    	if(StrUtil.isNotBlank(data_scope)) {
    		sql.append(" and r.data_scope = ? ");
    		paramList.add(data_scope);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and r.status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(r.create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(r.create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

    	//拼接排序语句
    	this.addOrderBySql(request, sql, "r.role_sort");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

	/**
	 * 根据用户ID查询权限
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public Set<String> selectRoleKeys(String userId) {
		List<Role> perms = selectRolesByUserId(userId);
		Set<String> permsSet = new HashSet<>();
		for (Role perm : perms) {
			if (perm != null) {
				permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
			}
		}
		return permsSet;
	}

	/**
	 * 根据用户ID查询角色
	 * @param userId
	 * @return 用户角色集合
	 */
	public List<Role> selectRolesByUserId(String userId) {
		String sql = "select distinct r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, " +
					 "		 r.status, r.del_flag, r.create_time, r.remark  " +
					 "	from sys_role r " +
					 "	left join sys_user_role ur on ur.role_id = r.role_id " +
					 "	left join sys_user u on u.user_id = ur.user_id " +
					 "	left join sys_dept d on u.dept_id = d.dept_id " +
					 " where r.del_flag = '0' and ur.user_id = ?";
		return db.queryForList(sql, new Object[]{userId}, Role.class);
	}

	/**
	 * 查询所有角色
	 * @return 角色列表
	 */
	public List<Role> selectRoleAll() {
		String sql = "select r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, r.status, r.del_flag, "+
 			   		 "	r.create_time, r.remark, if(r.status=0,'正常','停用') status_name, " +
 			   		 "	(case r.data_scope when 1 then '所有数据权限' when 2 then '自定义数据权限' when 3 then '本部门数据权限' " +
 			   		 "	when 4 then '本部门及以下数据权限' when 5 then '仅本人数据权限' else '' end) data_scope_name " +
 			   		 "  from sys_role r where r.status = '0' and r.del_flag = '0' order by r.role_sort";
		return db.queryForList(sql, null, Role.class);
	}

	/**
	 * 通过角色ID查询角色
	 * @param roleId 角色ID
	 * @return 角色对象信息
	 */
	public Role selectRoleById(String roleId) {
		String sql = "select r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, r.status, "+
					 "	r.del_flag, r.create_time, r.remark from sys_role r where r.del_flag = '0' and r.role_id = ? ";
		return db.queryForObject(sql, new Object[]{roleId}, Role.class);
	}

	/**
	 * 批量删除角色信息
	 * @param ids 需要删除的数据ID
	 */
	public int deleteRoleByIds(String ids) {
		String[] roleIds = Convert.toStrArray(ids);
		for (String roleId : roleIds) {
			Role role = selectRoleById(roleId);
			checkRoleAllowed(role);
			if (countUserRoleByRoleId(roleId) > 0) {
				throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
			}
		}

        //根据参数个数创建相应数量的占位符
        String placeholders = SqlUtil.rebuildInSql(roleIds.length);

		BatchSql batchSql = new BatchSql();
		String sql = "delete from sys_role_menu where role_id in ("+placeholders+")";
        batchSql.addBatch(sql, roleIds);

        sql = "delete from sys_role_dept where role_id in ("+placeholders+")";
        batchSql.addBatch(sql, roleIds);

    	sql = "update sys_role set del_flag = '2' where role_id in ("+placeholders+")";
        batchSql.addBatch(sql, roleIds);
		return db.doInTransaction(batchSql);
	}

	/**
	 * 新增保存角色信息
	 * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int insertRole(HttpServletRequest request) {
		String role_name = RequestUtil.getValue(request, "role_name");
    	String role_key = RequestUtil.getValue(request, "role_key");
    	String role_sort = RequestUtil.getValue(request, "role_sort");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

		//新增角色信息，返回自动增加的id号
		String sql = "insert into sys_role(role_name, role_key, role_sort, status, remark, create_by, create_time) "+
					 "values(?, ?, ?, ?, ?, ?, sysdate()) ";
		return db.execute(sql, new Object[]{role_name, role_key, role_sort, status, remark, operator_id});
	}

	/**
	 * 修改保存角色信息
     * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int updateRole(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
		String role_name = RequestUtil.getValue(request, "role_name");
    	String role_key = RequestUtil.getValue(request, "role_key");
    	String role_sort = RequestUtil.getValue(request, "role_sort");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

		//修改角色信息
		String sql = "update sys_role " +
					 "   set role_name = ?, role_key = ?, role_sort = ?, status = ?, " +
					 "   	 remark = ?, update_by = ?, update_time = sysdate() " +
					 " where role_id = ?";
		return db.execute(sql, new Object[]{role_name, role_key, role_sort, status, remark, operator_id, role_id});
	}

	/**
	 * 修改数据权限信息
     * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int authDataScope(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
    	String data_scope = RequestUtil.getValue(request, "data_scope");
    	String menuIds = RequestUtil.getValue(request, "menuIds");
    	String deptIds = RequestUtil.getValue(request, "deptIds");
    	String operator_id = ShiroUtils.getLoginName();

		BatchSql batchSql = new BatchSql();
		// 修改角色信息
		String sql = "update sys_role set data_scope = ?, update_by = ?, update_time = sysdate() where role_id = ?";
		batchSql.addBatch(sql, new Object[]{data_scope, operator_id, role_id});

		//删除角色与菜单关联
		sql = "delete from sys_role_menu where role_id = ?";
		batchSql.addBatch(sql, new Object[]{role_id});

		//插入角色菜单信息
		if(StrUtil.isNotBlank(menuIds)) {
			String[] menus = Convert.toStrArray(menuIds);
			sql = "insert into sys_role_menu(role_id, menu_id) values(?, ?)";
			for (String menu_id : menus) {
				batchSql.addBatch(sql, new Object[]{role_id, menu_id});
			}
		}

		// 删除角色与部门关联
		sql = "delete from sys_role_dept where role_id = ?";
		batchSql.addBatch(sql, new Object[]{role_id});
		// 新增角色和部门信息（数据权限）
		if(StrUtil.isNotBlank(deptIds)) {
			String[] depts = Convert.toStrArray(deptIds);
			sql = "insert into sys_role_dept(role_id, dept_id) values(?, ?)";
			for (String dept_id : depts) {
				batchSql.addBatch(sql, new Object[]{role_id, dept_id});
			}
		}

		return db.doInTransaction(batchSql);
	}

	/**
	 * 校验角色名称是否唯一
     * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int checkRoleNameUnique(HttpServletRequest request) {
    	String role_id = RequestUtil.getValue(request, "role_id");
		String role_name = RequestUtil.getValue(request, "role_name");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_role a where a.role_name = ? and a.del_flag <> 2 ";
		paramList.add(role_name);

		if(StrUtil.isNotBlank(role_id)) {
    		sql += " and a.role_id <> ? ";
    		paramList.add(role_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
	}

	/**
	 * 校验角色权限是否唯一
     * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int checkRoleKeyUnique(HttpServletRequest request) {
    	String role_id = RequestUtil.getValue(request, "role_id");
		String role_key = RequestUtil.getValue(request, "role_key");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_role a where a.role_key = ? and a.del_flag <> 2 ";
		paramList.add(role_key);

		if(StrUtil.isNotBlank(role_id)) {
    		sql += " and a.role_id <> ? ";
    		paramList.add(role_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
	}

	/**
	 * 校验角色是否允许操作
	 * @param role 角色信息
	 */
	public void checkRoleAllowed(Role role) {
		if (ObjectUtil.isNotNull(role.getRoleId()) && role.isAdmin()) {
			throw new BusinessException("不允许操作超级管理员角色");
		}
	}

	/**
	 * 通过角色ID查询角色使用数量
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public int countUserRoleByRoleId(String roleId) {
		String sql = "select count(1) from sys_user_role where role_id = ?";
		return db.queryForInt(sql, new Object[]{roleId});
	}

	/**
	 * 角色状态修改
     * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public int changeStatus(HttpServletRequest request) {
    	String role_id = RequestUtil.getValue(request, "role_id");
		String status = RequestUtil.getValue(request, "status");
    	String operator_id = ShiroUtils.getLoginName();
		//修改角色信息
		String sql = "update sys_role " +
					 "   set status = ?, update_by = ?, update_time = sysdate() " +
					 " where role_id = ?";
		return db.execute(sql, new Object[]{status, operator_id, role_id});
	}

	/**
	 * 批量取消授权用户角色
	 * @param roleId 角色ID
	 * @param userIds 需要删除的用户数据ID
	 * @return 结果
	 */
	public int deleteAuthUsers(String roleId, String userIds) {
    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_user_role where user_id in ("+SqlUtil.rebuildInSql(userIds, paramList)+") and role_id = ?";
    	paramList.add(roleId);
		return db.execute(sql, paramList.toArray());
	}

	/**
	 * 批量选择授权用户角色
	 * @param roleId 角色ID
	 * @param userIds 需要删除的用户数据ID
	 * @return 结果
	 */
	public int insertAuthUsers(String roleId, String userIds) {
		String[] users = Convert.toStrArray(userIds);

		BatchSql batchSql = new BatchSql();
		String sql = "insert into sys_user_role(user_id, role_id) values(?, ?)";
		// 新增用户与角色管理
		for (String user_id : users) {
			batchSql.addBatch(sql, new Object[]{user_id, roleId});
		}
		return db.doInTransaction(batchSql);
	}
}
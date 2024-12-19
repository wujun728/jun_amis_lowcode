package com.ruoyi.project.system.dept.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.utils.StrUtils;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.common.CommonService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 部门管理 服务实现
 * @author ruoyi
 */
@Service
public class DeptService extends CommonService {

	/**
	 * 查询部门管理数据
	 * @param request
	 * @return 部门信息集合
	 */
	public List<Map<String, Object>> selectDeptList(HttpServletRequest request) {
		String parent_id = RequestUtil.getValue(request, "parent_id");
    	String dept_name = RequestUtil.getValue(request, "dept_name");
    	String status = RequestUtil.getValue(request, "status");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();

    	sql.append("select d.dept_id, d.parent_id, d.ancestors, d.dept_name, d.order_num, d.leader, d.phone, "
    			+ "d.email, d.status, d.del_flag, d.create_by, d.create_time from sys_dept d where d.del_flag = '0' ");

    	if(StrUtil.isNotBlank(dept_name)) {
    		sql.append(" and d.dept_name like concat('%', ?, '%') ");
    		paramList.add(dept_name);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and d.status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(parent_id)) {
    		sql.append(" and d.parent_id = ? ");
    		paramList.add(parent_id);
    	}

    	//数据范围过滤
    	this.dataScopeFilter(sql, paramList, "d", null);

    	//排序
    	sql.append(" order by d.parent_id, d.order_num");

    	logger.debug("查询部门："+SqlUtil.getSql(sql.toString(), paramList));
		return db.queryForList(sql.toString(), paramList.toArray());
	}

	/**
	 * 查询部门管理树
	 * @param request
	 * @return 所有部门信息
	 */
	public List<Ztree> selectDeptTree(HttpServletRequest request) {
		List<Map<String, Object>> deptList = this.selectDeptList(request);
		List<Ztree> ztrees = initZtree(deptList);
		return ztrees;
	}

    /**
     * 查询部门管理树（排除下级）
     * @param deptId 部门ID
     * @return 所有部门信息
     */
    public List<Ztree> selectDeptTreeExcludeChild(String deptId) {
        List<Map<String, Object>> deptList = this.selectDeptList(null);
        Iterator<Map<String, Object>> it = deptList.iterator();
        while (it.hasNext()) {
            Map<String, Object> map = it.next();
            if(deptId.equals(MapUtil.getStr(map, "dept_id"))
                || ArrayUtil.contains(StrUtils.split(MapUtil.getStr(map, "ancestors")+ "", ","), deptId+"")) {
                it.remove();
            }
        }
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

	/**
	 * 根据角色ID查询部门（数据权限）
	 * @param request
	 * @return 部门列表（数据权限）
	 */
	public List<Ztree> roleDeptTreeData(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
		List<Ztree> ztrees = new ArrayList<Ztree>();
		List<Map<String, Object>> deptList = this.selectDeptList(request);
		if (StrUtil.isNotBlank(role_id)) {
			String sql = "select concat(d.dept_id, d.dept_name) as dept_name " +
						 "	from sys_dept d " +
						 "	left join sys_role_dept rd on d.dept_id = rd.dept_id " +
						 " where d.del_flag = '0' and rd.role_id = ? " +
						 " order by d.parent_id, d.order_num";
			List<String> roleDeptList = db.queryForList(sql, new Object[]{role_id}, "dept_name");
			ztrees = initZtree(deptList, roleDeptList);
		}
		else {
			ztrees = initZtree(deptList);
		}
		return ztrees;
	}

	/**
	 * 对象转部门树
	 * @param deptList 部门列表
	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<Map<String, Object>> deptList) {
		return initZtree(deptList, null);
	}

	/**
	 * 对象转部门树
	 * @param deptList 部门列表
	 * @param roleDeptList 角色已存在菜单列表
	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<Map<String, Object>> deptList, List<String> roleDeptList) {
		List<Ztree> ztrees = new ArrayList<Ztree>();
		boolean isCheck = CollUtil.isNotEmpty(roleDeptList);
		for (Map<String, Object> dept : deptList) {
			if (UserConstants.DEPT_NORMAL.equals(MapUtil.getStr(dept, "status"))) {
				Ztree ztree = new Ztree();
				ztree.setId(MapUtil.getLong(dept, "dept_id"));
				ztree.setpId(MapUtil.getLong(dept, "parent_id"));
				ztree.setName(MapUtil.getStr(dept, "dept_name"));
				ztree.setTitle(MapUtil.getStr(dept, "dept_name"));
				if (isCheck) {
					ztree.setChecked(roleDeptList.contains(MapUtil.getLong(dept, "dept_id") + MapUtil.getStr(dept, "dept_name")));
				}
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}

	/**
	 * 查询部门人数
	 * @param parent_id 部门ID
	 * @return 结果
	 */
	public int selectDeptCount(String parent_id, String dept_id) {
		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_dept a where del_flag = '0'";

		if(StrUtil.isNotBlank(parent_id)) {
			sql += " and a.parent_id = ? ";
			paramList.add(parent_id);
		}
		if(StrUtil.isNotBlank(dept_id)) {
    		sql += " and a.dept_id = ? ";
    		paramList.add(dept_id);
    	}

		return db.queryForInt(sql, paramList.toArray());
	}

	/**
	 * 查询部门是否存在用户
	 * @param deptId 部门ID
	 * @return 结果 true 存在 false 不存在
	 */
	public boolean checkDeptExistUser(String deptId) {
		String sql = "select count(1) from sys_user where dept_id = ? and del_flag = '0'";
		int result = db.queryForInt(sql, new Object[]{deptId});
		return result > 0 ? true : false;
	}

	/**
	 * 删除部门管理信息
	 * @param deptId 部门ID
	 * @return 结果
	 */
	public int deleteDeptById(String deptId) {
		String sql = "update sys_dept set del_flag = '2' where dept_id = ?";
		return db.execute(sql, new Object[]{deptId});
	}

    /**
     * 根据ID查询所有子部门（正常状态）
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(String deptId) {
        String sql = "select count(1) from sys_dept where status = 0 and del_flag = '0' and find_in_set(#{deptId}, ancestors)";
        return db.queryForInt(sql, new Object[]{deptId});
    }

    /**
     * 保存部门
     * @param request
     * @return 结果
     */
    public int saveDept(HttpServletRequest request) {
    	String dept_id = RequestUtil.getValue(request, "dept_id");
		String dept_name = RequestUtil.getValue(request, "dept_name");
    	String parent_id = RequestUtil.getValue(request, "parent_id");
    	String order_num = RequestUtil.getValue(request, "order_num");
    	String leader = RequestUtil.getValue(request, "leader");
    	String phone = RequestUtil.getValue(request, "phone");
    	String email = RequestUtil.getValue(request, "email");
    	String status = RequestUtil.getValue(request, "status");
    	String operator_id = ShiroUtils.getLoginName();
    	//查询上级部门信息
    	Map<String, Object> parentDept = this.selectDeptById(parent_id);
    	String ancestors = MapUtil.getStr(parentDept, "ancestors")+ "," +parent_id;

    	if(!"".equals(dept_id)) {
    		//查询该部门保存前的信息
    		Map<String, Object> oldDept = this.selectDeptById(dept_id);
    		if (MapUtil.isNotEmpty(parentDept) && MapUtil.isNotEmpty(oldDept)) {
    			String oldAncestors = MapUtil.getStr(oldDept, "ancestors");
    			updateDeptChildren(dept_id, ancestors, oldAncestors);
    		}

    		String sql = "update sys_dept a "+
    				 	 "   set dept_name = ?, parent_id = ?, ancestors = ?, order_num = ?, leader = ?," +
    				 	 "		 phone = ?, email = ?, status = ?, update_by = ?, update_time = sysdate() " +
    				 	 " where dept_id = ?";
    		return db.execute(sql, new Object[]{dept_name, parent_id, ancestors, order_num, leader, phone, email, status, operator_id, dept_id});
    	}

    	if (!UserConstants.DEPT_NORMAL.equals(MapUtil.getStr(parentDept, "status"))) {
			throw new BusinessException("部门停用，不允许新增");
		}

    	String sql = "insert into sys_dept(dept_name, parent_id, ancestors, order_num, leader, phone, email, status, create_by, create_time) " +
    				 "values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
        return db.execute(sql, new Object[]{dept_name, parent_id, ancestors, order_num, leader, phone, email, status, operator_id});
    }

	/**
	 * 修改子元素关系
	 * @param dept_id 被修改的部门ID
	 * @param newAncestors 新的父ID集合
	 * @param oldAncestors 旧的父ID集合
	 */
	public void updateDeptChildren(String dept_id, String newAncestors, String oldAncestors) {
		String sql = "select * from sys_dept where find_in_set(?, ancestors)";
		List<Map<String, Object>> children = db.queryForList(sql, new Object[]{dept_id});

		if (children.size() > 0) {
			StringBuffer setSql = new StringBuffer("(case dept_id");
			StringBuffer whereSql = new StringBuffer(" where dept_id in (");

			String ancestors = "";
			String deptId = "";
			for (Map<String, Object> child : children) {
				deptId = MapUtil.getStr(child, "dept_id");
				ancestors = MapUtil.getStr(child, "ancestors").replaceFirst(oldAncestors, newAncestors);

				setSql.append(" when "+deptId+" then '"+ancestors+"'");
				whereSql.append(deptId+",");
			}

			setSql.append(" end) ");
			whereSql = whereSql.deleteCharAt(whereSql.length() - 1);
			whereSql.append(") ");

			sql = "update sys_dept set ancestors = "+setSql.toString()+whereSql.toString();
			db.execute(sql, null);
		}
	}

	/**
	 * 根据部门ID查询信息
	 * @param dept_id 部门ID
	 * @return 部门信息
	 */
	public Map<String, Object> selectDeptById(String dept_id) {
		String sql = "select d.dept_id, d.parent_id, d.ancestors, d.dept_name, d.order_num, d.leader, d.phone, d.email, " +
					 "		 d.status, (select dept_name from sys_dept where dept_id = d.parent_id) parent_name " +
					 "  from sys_dept d " +
					 " where d.dept_id = ?";
		return db.queryForMap(sql, new Object[]{dept_id});
	}

	/**
	 * 校验部门名称是否唯一
     * @param request
	 * @return 结果
	 */
	public int checkDeptNameUnique(HttpServletRequest request) {
		String dept_id = RequestUtil.getValue(request, "dept_id");
		String parent_id = RequestUtil.getValue(request, "parent_id");
    	String dept_name = RequestUtil.getValue(request, "dept_name");

		List<String> paramList = new ArrayList<String>();
    	String sql = "select count(1) from sys_dept where del_flag = '0' and dept_name = ? and parent_id = ?";
		paramList.add(dept_name);
		paramList.add(parent_id);

		if(StrUtil.isNotBlank(dept_id)) {
    		sql += " and dept_id <> ? ";
    		paramList.add(dept_id);
    	}
    	return db.queryForInt(sql, paramList.toArray());
	}
}
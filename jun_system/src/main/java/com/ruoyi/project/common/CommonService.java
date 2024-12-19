package com.ruoyi.project.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.db.DBUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.DataScopeAspect;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.user.domain.User;

import cn.hutool.core.util.StrUtil;

/**
 * Service业务层基础类
 * @author daixf
 * @date 2019-08-24
 */
@Service
public class CommonService {
	public static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	public DBUtils db;

	/**
	 * 返回表格分页数据对象
	 * @param request
	 * @param sql 查询语句
	 * @param paramList 查询参数集
	 * @param pagination 是否分页查询
	 * @return
	 */
	public TableDataInfo getRespTableDataInfo(HttpServletRequest request, String sql, List<String> paramList, boolean pagination) {
		// 查询结果集
		List<Map<String, Object>> dataList = null;
		long total = 0L;

		if(pagination) {//分页查询
			dataList = db.getForList(sql.toString(), paramList, request);
			total = db.getTotalRows(sql, paramList);
		}
		else {
			dataList = db.queryForList(sql, paramList.toArray());
			total = dataList.size();
		}

		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(dataList);
		rspData.setTotal(total);
		return rspData;
	}

	/**
	 * 拼接排序语句
	 * @param request HttpServletRequest对象
	 * @param sql sql语句
	 * @param defaultOrderBy 默认排序字段，可多字段，页面未传orderByColumn时，该排序字段有效
	 */
	public void addOrderBySql(HttpServletRequest request, StringBuffer sql, String defaultOrderBy) {
		String sortName = RequestUtil.getValue(request, "orderByColumn");
		String isAsc = RequestUtil.getValue(request, "isAsc");

		sql.append(" order by ");
		if(StrUtil.isNotBlank(sortName)) {
			sql.append(SqlUtil.escapeOrderBySql(sortName+" "+isAsc));
		}
		else {
			sql.append(defaultOrderBy);
		}
	}

	/**
	 * 数据范围过滤
	 * @param sql 语句
	 * @param paramList 参数集合
	 * @param deptAlias 部门表的别名
	 * @param userAlias 用户表的别名
	 */
	public void dataScopeFilter(StringBuffer sql, List<String> paramList, String deptAlias, String userAlias) {
		User user = ShiroUtils.getSysUser();

		List<Role> userRoles = user.getRoles();
		if(userRoles != null && userRoles.size() > 0) {
			StringBuilder sqlString = new StringBuilder();
			for (Role role : userRoles) {
				String dataScope = role.getDataScope();
				if (DataScopeAspect.DATA_SCOPE_ALL.equals(dataScope)) {//全部数据权限
					break;
				}
				else if (DataScopeAspect.DATA_SCOPE_CUSTOM.equals(dataScope)) {//自定数据权限
					sqlString.append(" or "+deptAlias+".dept_id in (select dept_id from sys_role_dept where role_id = ?) ");
					paramList.add(String.valueOf(role.getRoleId()));
				}
				else if (DataScopeAspect.DATA_SCOPE_DEPT.equals(dataScope)) {//部门数据权限
					sqlString.append(" or "+deptAlias+".dept_id = ? ");
					paramList.add(String.valueOf(user.getDeptId()));
				}
				else if (DataScopeAspect.DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {//部门及以下数据权限
					sqlString.append(" or "+deptAlias+".dept_id in (select dept_id from sys_dept where dept_id = ? or find_in_set(?, ancestors)) ");
					paramList.add(String.valueOf(user.getDeptId()));
					paramList.add(String.valueOf(user.getDeptId()));
				}
				else if (DataScopeAspect.DATA_SCOPE_SELF.equals(dataScope)) {//仅本人数据权限
					if (StrUtil.isNotBlank(userAlias)) {
						sqlString.append(" or "+userAlias+".user_id = ? ");
						paramList.add(String.valueOf(user.getUserId()));
					}
					else {
						// 数据权限为仅本人且没有userAlias别名不查询任何数据
						sqlString.append(" or 1=0 ");
					}
				}
			}

			if (StrUtil.isNotBlank(sqlString.toString())) {
				sql.append(" and (" + sqlString.substring(4) + ")");
			}
		}
	}
}
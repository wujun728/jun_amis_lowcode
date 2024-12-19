package com.ruoyi.project.system.user.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.service.RoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户 业务层处理
 * @author ruoyi
 */
@Service
public class UserService extends CommonService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    /**
     * 根据条件分页查询用户列表
     * @param request
     * @return
     */
    public TableDataInfo selectUserList(HttpServletRequest request, boolean pagination) {
		String login_name = RequestUtil.getValue(request, "login_name");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");
    	String status = RequestUtil.getValue(request, "status");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");
    	String dept_id = RequestUtil.getValue(request, "dept_id");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
		sql.append("select u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, u.password, u.sex, u.salt, " +
				   "	   u.status, u.del_flag, u.login_ip, u.login_date, u.create_by, u.create_time, u.remark, d.dept_name, d.leader, " +
				   "	   (select m1.dict_label from sys_dict_data m1 where m1.dict_type = 'sys_user_sex' and u.sex = m1.dict_value) sex_name, " +
				   "	   if(u.status=0,'正常','停用') status_name " +
				   "  from sys_user u " +
				   "  left join sys_dept d on u.dept_id = d.dept_id " +
				   " where u.del_flag = '0'");

    	if(StrUtil.isNotBlank(login_name)) {
    		sql.append(" and u.login_name like concat('%', ?, '%') ");
    		paramList.add(login_name);
    	}
    	if(StrUtil.isNotBlank(phonenumber)) {
    		sql.append(" and u.phonenumber like concat('%', ?, '%') ");
    		paramList.add(phonenumber);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and u.status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(u.create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(u.create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}
    	if(StrUtil.isNotBlank(dept_id)) {
    		sql.append(" and (u.dept_id = ? or u.dept_id in (select t.dept_id from sys_dept t where find_in_set (?, ancestors))) ");
    		paramList.add(dept_id);
    		paramList.add(dept_id);
    	}

    	//数据范围过滤
    	this.dataScopeFilter(sql, paramList, "d", "u");

		//拼接排序语句
		this.addOrderBySql(request, sql, "u.create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     * @param request
     * @return 用户信息集合信息
     */
	public TableDataInfo selectAllocatedList(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select distinct u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, " +
    			   "	   u.status, u.create_time, d.parent_id, d.dept_name, d.order_num, d.leader " +
    			   "  from sys_user u left join sys_dept d on u.dept_id = d.dept_id " +
    			   " where u.del_flag = '0' " +
    			   "   and exists(select 1 from sys_user_role ur where u.user_id = ur.user_id and ur.role_id = ?) ");
		paramList.add(role_id);

    	if(StrUtil.isNotBlank(login_name)) {
    		sql.append(" and u.login_name like concat('%', ?, '%') ");
    		paramList.add(login_name);
    	}
    	if(StrUtil.isNotBlank(phonenumber)) {
    		sql.append(" and u.phonenumber like concat('%', ?, '%') ");
    		paramList.add(phonenumber);
    	}

    	//数据范围过滤
    	this.dataScopeFilter(sql, paramList, "d", "u");

		//拼接排序语句
		this.addOrderBySql(request, sql, "u.create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
	}

    /**
     * 根据条件分页查询未分配用户角色列表
	 * @param request HttpServletRequest对象
     * @return 用户信息集合信息
     */
	public TableDataInfo selectUnallocatedList(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select distinct u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, " +
    			   "	   u.status, u.create_time, d.parent_id, d.dept_name, d.order_num, d.leader  " +
    			   "  from sys_user u left join sys_dept d on u.dept_id = d.dept_id " +
    			   " where u.del_flag = '0' "+
 			   	   "  and not exists(select 1 from sys_user_role ur where u.user_id = ur.user_id and ur.role_id = ?) ");
		paramList.add(role_id);

    	if(StrUtil.isNotBlank(login_name)) {
    		sql.append(" and u.login_name like concat('%', ?, '%') ");
    		paramList.add(login_name);
    	}
    	if(StrUtil.isNotBlank(phonenumber)) {
    		sql.append(" and u.phonenumber like concat('%', ?, '%') ");
    		paramList.add(phonenumber);
    	}

    	//数据范围过滤
    	this.dataScopeFilter(sql, paramList, "d", "u");

		//拼接排序语句
		this.addOrderBySql(request, sql, "u.create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
	}

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
	public User selectUserByLoginName(String userName) {
		String sql = "select u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, u.sex, " +
					 "       u.password, u.salt, u.status, u.del_flag, u.login_ip, u.login_date, u.pwd_update_date, u.create_time, " +
					 "		 u.remark, d.dept_id, d.parent_id, d.dept_name, d.order_num, d.leader, d.status as dept_status " +
					 "  from sys_user u " +
					 "	left join sys_dept d on u.dept_id = d.dept_id " +
					 " where u.login_name = ?";
		return db.queryForObject(sql, new Object[]{userName}, User.class);
	}

    /**
     * 通过手机号码查询用户
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
	public User selectUserByPhoneNumber(String phoneNumber) {
		String sql = "select u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, u.sex, " +
				 	 "       u.password, u.salt, u.status, u.del_flag, u.login_ip, u.login_date, u.pwd_update_date, u.create_time, " +
				 	 "		 u.remark, d.parent_id, d.dept_name, d.order_num, d.leader, d.status as dept_status " +
				 	 "  from sys_user u " +
				 	 "	left join sys_dept d on u.dept_id = d.dept_id " +
				 	 " where u.phonenumber = ?";
		return db.queryForObject(sql, new Object[]{phoneNumber}, User.class);
	}

    /**
     * 通过邮箱查询用户
     * @param email 邮箱
     * @return 用户对象信息
     */
	public User selectUserByEmail(String email) {
		String sql = "select u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, u.sex, " +
			 	 	 "       u.password, u.salt, u.status, u.del_flag, u.login_ip, u.login_date, u.pwd_update_date, u.create_time, " +
			 	 	 "		 u.remark, d.parent_id, d.dept_name, d.order_num, d.leader, d.status as dept_status " +
			 	 	 "  from sys_user u " +
			 	 	 "	left join sys_dept d on u.dept_id = d.dept_id " +
			 	 	 " where u.email = ?";
		return db.queryForObject(sql, new Object[]{email}, User.class);
	}

    /**
     * 通过用户ID查询用户
     * @param user_id 用户ID
     * @return 用户对象信息
     */
	public User selectUserById(String user_id) {
		String sql = "select u.user_id, u.dept_id, u.login_name, u.user_name, u.user_type, u.email, u.avatar, u.phonenumber, u.sex, " +
		 	 	 	 "       u.password, u.salt, u.status, u.del_flag, u.login_ip, u.login_date, u.pwd_update_date, u.create_time, " +
		 	 	 	 "		 u.remark, d.parent_id, d.dept_name, d.order_num, d.leader, d.status as dept_status, " +
		 	 	 	 "		 (select group_concat(p.post_id) from sys_user_post p where u.user_id = p.user_id) post_ids, " +
		 	 	 	 "		 (select group_concat(r.role_id) from sys_user_role r where u.user_id = r.user_id) role_ids " +
		 	 	 	 "  from sys_user u " +
		 	 	 	 "	left join sys_dept d on u.dept_id = d.dept_id " +
		 	 	 	 " where u.user_id = ?";
		return db.queryForObject(sql, new Object[]{user_id}, User.class);
	}

    /**
     * 通过用户ID查询用户
     * @param user_id 用户ID
     * @return 用户对象信息
     */
	public User selectUserById(Long user_id) {
		return this.selectUserById(String.valueOf(user_id));
	}

    /**
     * 通过用户ID查询用户和角色关联
     * @param user_id 用户ID
     * @return 用户和角色关联列表
     */
    public List<UserRole> selectUserRoleByUserId(String user_id) {
        String sql = "select user_id, role_id from sys_user_role where user_id = ?";
        return db.queryForList(sql, new Object[]{user_id}, UserRole.class);
    }

    /**
     * 批量删除用户信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserByIds(String ids) {
		if(StrUtil.isBlank(ids) || ",".equals(ids)) {
            throw new BusinessException("至少选择删除一个用户");
        }

        Long[] userIds = Convert.toLongArray(ids);
		for (Long userId : userIds) {
			if (User.isAdmin(userId)) {
				throw new BusinessException("不允许删除超级管理员用户");
			}
		}

		//根据参数个数创建相应数量的占位符
		String placeholders = SqlUtil.rebuildInSql(userIds.length);
        BatchSql batchSql = new BatchSql();
        String sql = "delete from sys_user_role where user_id in ("+placeholders+")";
        batchSql.addBatch(sql, userIds);

        sql = "delete from sys_user_post where user_id in ("+placeholders+")";
        batchSql.addBatch(sql, userIds);

    	sql = "update sys_user set del_flag = '2' where user_id in ("+placeholders+")";
        batchSql.addBatch(sql, userIds);
		return db.doInTransaction(batchSql);
	}

    /**
     * 新增保存用户信息
	 * @param request HttpServletRequest对象
     * @return 结果
     */
	public int insertUser(HttpServletRequest request) {
    	String dept_id = RequestUtil.getValue(request, "dept_id");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String user_name = RequestUtil.getValue(request, "user_name");
    	String email = RequestUtil.getValue(request, "email");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");
    	String sex = RequestUtil.getValue(request, "sex");
    	String password = RequestUtil.getValue(request, "password");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String postIds = RequestUtil.getValue(request, "postIds");
    	String roleIds = RequestUtil.getValue(request, "roleIds");
    	String operator_id = ShiroUtils.getLoginName();
    	String salt = User.getRandomSalt();
    	password = passwordService.encryptPassword(login_name, password, salt);

		//新增用户信息，返回自动增加的用户id号
		String sql = "insert into sys_user(dept_id, login_name, user_name, email, phonenumber, sex, password, "+
					 "salt, status, remark, create_by, create_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate()) ";
		long user_id = db.insert(sql, new String[]{dept_id, login_name, user_name, email, phonenumber, sex,
				password, salt, status, remark, operator_id});

		BatchSql batchSql = new BatchSql();
		// 新增用户岗位关联
		insertUserPost(batchSql, String.valueOf(user_id), postIds);
		// 新增用户与角色管理
		insertUserRole(batchSql, String.valueOf(user_id), roleIds);
		return db.doInTransaction(batchSql);
	}

    /**
     * 注册用户信息
     * @param request HttpServletRequest对象
     * @return 结果
     */
    public boolean registerUser(HttpServletRequest request) {
        String login_name = RequestUtil.getValue(request, "login_name");
        String password = RequestUtil.getValue(request, "password");
        String salt = User.getRandomSalt();
        password = passwordService.encryptPassword(login_name, password, salt);

        //新增注册用户信息
        String sql = "insert into sys_user(login_name, user_type, password, salt, status, create_by, create_time) " +
                     "values(?, ?, ?, ?, 0, ?, sysdate()) ";
        return db.execute(sql, new String[]{login_name, UserConstants.REGISTER_USER_TYPE, password, salt, login_name}) > 0;
    }

    /**
     * 新增用户岗位信息
     * @param batchSql 批处理
	 * @param user_id 用户ID
	 * @param postIds 岗位IDs
     */
	public void insertUserPost(BatchSql batchSql, String user_id, String postIds) {
		if(StrUtil.isNotBlank(postIds)) {
			String[] posts = Convert.toStrArray(postIds);
			String sql = "insert into sys_user_post(user_id, post_id) values(?, ?)";
			for (String post_id : posts) {
				batchSql.addBatch(sql, new Object[]{user_id, post_id});
			}
		}
	}

    /**
     * 新增用户角色信息
	 * @param batchSql 批处理
	 * @param user_id 用户ID
	 * @param roleIds 角色IDs
     */
	public void insertUserRole(BatchSql batchSql, String user_id, String roleIds) {
		if(StrUtil.isNotBlank(roleIds)) {
			String[] roles = Convert.toStrArray(roleIds);
			String sql = "insert into sys_user_role(user_id, role_id) values(?, ?)";
			for (String role_id : roles) {
				batchSql.addBatch(sql, new Object[]{user_id, role_id});
			}
		}
	}

    /**
     * 修改保存用户信息
	 * @param request HttpServletRequest对象
     * @return 结果
     */
	public int updateUser(HttpServletRequest request) {
    	String user_id = RequestUtil.getValue(request, "user_id");
    	String dept_id = RequestUtil.getValue(request, "dept_id");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String user_name = RequestUtil.getValue(request, "user_name");
    	String email = RequestUtil.getValue(request, "email");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");
    	String sex = RequestUtil.getValue(request, "sex");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String postIds = RequestUtil.getValue(request, "postIds");
    	String roleIds = RequestUtil.getValue(request, "roleIds");
    	String operator_id = ShiroUtils.getLoginName();

    	BatchSql batchSql = new BatchSql();
		//修改用户信息
		String sql = "update sys_user " +
					 "   set dept_id = ?, login_name = ?, user_name = ?, email = ?, phonenumber = ?, sex = ?, status = ?, " +
					 "   	 remark = ?, update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		batchSql.addBatch(sql, new Object[]{dept_id, login_name, user_name, email, phonenumber, sex, status, remark, operator_id, user_id});

		//删除用户与角色关联
		sql = "delete from sys_user_role where user_id = ?";
		batchSql.addBatch(sql, new Object[]{user_id});

		//删除用户与岗位关联
		sql = "delete from sys_user_post where user_id = ?";
		batchSql.addBatch(sql, new Object[]{user_id});

		// 新增用户与角色管理
		insertUserRole(batchSql, user_id, roleIds);
		// 新增用户与岗位管理
		insertUserPost(batchSql, user_id, postIds);
		return db.doInTransaction(batchSql);
	}

    /**
     * 修改用户密码
	 * @param request HttpServletRequest对象
     * @return 结果
     */
	public int resetUserPwd(HttpServletRequest request) {
    	String user_id = RequestUtil.getValue(request, "user_id");
    	String login_name = RequestUtil.getValue(request, "login_name");
    	String password = RequestUtil.getValue(request, "password");
    	String operator_id = ShiroUtils.getLoginName();
    	String salt = User.getRandomSalt();
    	password = passwordService.encryptPassword(login_name, password, salt);

		//修改用户密码
		String sql = "update sys_user " +
					 "   set salt = ?, password = ?, update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		return db.execute(sql, new Object[]{salt, password, operator_id, user_id});
	}

    /**
     * 校验登录名称是否唯一
	 * @param request HttpServletRequest对象
     * @return
     */
	public int checkLoginNameUnique(HttpServletRequest request) {
    	String user_id = RequestUtil.getValue(request, "user_id");
		String login_name = RequestUtil.getValue(request, "login_name");

		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_user where login_name = ? ";
		paramList.add(login_name);

		if(StrUtil.isNotBlank(user_id)) {
    		sql += " and user_id <> ? ";
    		paramList.add(user_id);
    	}
		return db.queryForInt(sql, paramList.toArray());
	}

    /**
     * 校验用户名称是否唯一
	 * @param request HttpServletRequest对象
     * @return
     */
	public int checkPhoneUnique(String phonenumber, String userId) {
		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_user where phonenumber = ? ";
		paramList.add(phonenumber);

		if(StrUtil.isNotBlank(userId)) {
    		sql += " and user_id <> ? ";
    		paramList.add(userId);
    	}
		logger.debug("校验用户名称是否唯一："+SqlUtil.getSql(sql, paramList));
		return db.queryForInt(sql, paramList.toArray());
	}

    /**
     * 校验email是否唯一
	 * @param request HttpServletRequest对象
     * @return
     */
	public int checkEmailUnique(String email, String userId) {
		List<String> paramList = new ArrayList<String>();
		String sql = "select count(1) from sys_user where email = ? ";
		paramList.add(email);

		if(StrUtil.isNotBlank(userId)) {
    		sql += " and user_id <> ? ";
    		paramList.add(userId);
    	}
		return db.queryForInt(sql, paramList.toArray());
	}

    /**
     * 查询用户所属角色组
     * @param userId 用户ID
     * @return 结果
     */
	public String selectUserRoleGroup(String userId) {
		List<Role> list = roleService.selectRolesByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (Role role : list) {
			idsStr.append(role.getRoleName()).append(",");
		}
		if (StrUtil.isNotBlank(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

    /**
     * 查询用户所属岗位组
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserPostGroup(String userId) {
    	String sql = "SELECT p.post_id, p.post_name, p.post_code " +
    				 "  FROM sys_user u " +
    				 " LEFT JOIN sys_user_post up ON u.user_id = up.user_id " +
    				 " LEFT JOIN sys_post p ON up.post_id = p.post_id " +
    				 " WHERE up.user_id = ? ";
    	List<Map<String, Object>> list = db.queryForList(sql, new Object[]{userId});

        StringBuffer idsStr = new StringBuffer();
    	for (Map<String, Object> post : list) {
            idsStr.append(MapUtil.getStr(post, "post_name")).append(",");
        }

        if (StrUtil.isNotBlank(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

//    /**
//     * 导入用户数据
//     * @param userList 用户数据列表
//     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
//     * @return 结果
//     */
//	public String importUser(List<User> userList, Boolean isUpdateSupport) {
//		if (StringUtils.isNull(userList) || userList.size() == 0) {
//			throw new BusinessException("导入用户数据不能为空！");
//		}
//		int successNum = 0;
//		int failureNum = 0;
//		StringBuilder successMsg = new StringBuilder();
//		StringBuilder failureMsg = new StringBuilder();
//		String operName = ShiroUtils.getLoginName();
//		String password = paramService.selectConfigByKey("sys.user.initPassword");
//		for (User user : userList) {
//			try {
//				// 验证是否存在这个用户
//				User u = userMapper.selectUserByLoginName(user.getLoginName());
//				if (StringUtils.isNull(u)) {
//					user.setPassword(password);
//					user.setCreateBy(operName);
//					this.insertUser(user);
//					successNum++;
//					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
//				} else if (isUpdateSupport) {
//					user.setUpdateBy(operName);
//					this.updateUser(user);
//					successNum++;
//					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
//				} else {
//					failureNum++;
//					failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
//				}
//			} catch (Exception e) {
//				failureNum++;
//				String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
//				failureMsg.append(msg + e.getMessage());
//				logger.error(msg, e);
//			}
//		}
//		if (failureNum > 0) {
//			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
//			throw new BusinessException(failureMsg.toString());
//		} else {
//			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
//		}
//		return successMsg.toString();
//	}

    /**
     * 用户状态修改
	 * @param request HttpServletRequest对象
     * @return 结果
     */
	public int changeStatus(HttpServletRequest request) {
    	String user_id = RequestUtil.getValue(request, "user_id");
    	String status = RequestUtil.getValue(request, "status");
    	String operator_id = ShiroUtils.getLoginName();

		if (User.isAdmin(Long.parseLong(user_id))) {
			throw new BusinessException("不允许修改超级管理员用户");
		}

		//修改用户状态
		String sql = "update sys_user " +
					 "   set status = ?, update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		return db.execute(sql, new Object[]{status, operator_id, user_id});
	}
}
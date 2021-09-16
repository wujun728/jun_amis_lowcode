package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.common.AppUtils;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Dept;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.mapper.RoleMapper;
import com.jun.plugin.system.mapper.UserMapper;
import com.jun.plugin.system.service.DeptService;
import com.jun.plugin.system.service.UserService;
import com.jun.plugin.system.vo.DeptVo;
import com.jun.plugin.system.vo.UserVo;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: UserServiceImpl Description: layui date: 2020/4/14 19:50
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	/**
	 * 日志提示信息
	 */
	private Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 根据用户名查询用户登陆信息
	 *
	 * @param loingname
	 */
	@Override
	public User queryUserByLoginName(String loingname) {
		UserMapper baseMapper = this.getBaseMapper();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		/**
		 * 登陆逻辑 如果传入为空 则直接返回空
		 */
		if (StringUtils.isBlank(loingname)) {
			log.error("登陆名不能为空");
			return null;
		}
		queryWrapper.eq("loginname", loingname);
		return baseMapper.selectOne(queryWrapper);
	}

	/**
	 * 加载用户列表
	 *
	 * @param userVo
	 * @return
	 */
	@Override
	public DataGridView queryAllUser(UserVo userVo) {
		IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("type", Constant.USER_TYPE_NORMAL);
		qw.eq(null != userVo.getDeptid(), "deptid", userVo.getDeptid());
		qw.like(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
		qw.like(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
		qw.like(StringUtils.isNotBlank(userVo.getRemark()), "remark", userVo.getRemark());
		this.userMapper.selectPage(page, qw);
		// 获取查询用户列表
		List<User> records = page.getRecords();
		// 处理 前端显示部门名称\

		// 直接往ioc容器当中获取 因为这个时候 ioc容器的代理都加载完毕 不会受shiro影响
		DeptService deptService = AppUtils.getContext().getBean(DeptService.class);
		// 遍历所有用户
		for (User record : records) {
			if (null != record.getDeptid()) {
				// 根据部门id 查询 部门信息
				Dept dept = deptService.getById(record.getDeptid());
				// 储存 部门名称
				record.setDeptname(dept.getTitle());
			}
		}
		log.info("用户列表加载完毕");
		// 返回
		return new DataGridView(page.getTotal(), records);
	}

	/**
	 * 删除用户 和 用户之间的关系
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {
		// 根据用户id删除 用户和角色 关系表
		this.roleMapper.deleteRoleUserByUid(id);
		// 删除用户
		return super.removeById(id);
	}

	/**
	 * 保存用户
	 *
	 * @param user
	 * @return
	 */
	@Override
	public User saveUser(User user) {
		this.userMapper.insert(user);
		return user;
	}

	/**
	 * 更新用户
	 *
	 * @param user
	 * @return
	 */
	@Override
	public User updateUser(User user) {
		this.userMapper.updateById(user);
		return user;
	}

	/**
	 * 查询用户最大排序码
	 *
	 * @return
	 */
	@Override
	public Integer queryUserMaxOrderNum() {
		return this.userMapper.queryUserMaxOrderNum();
	}

	@Override
	public void saveUserRole(Integer uid, Integer[] rids) {
		// 根据uid删除该用户所有角色
		this.roleMapper.deleteRoleUserByUid(uid);
		if (null != rids) {
			// 进行添加
			this.roleMapper.insertUserRole(uid, rids);
		}
	}
}

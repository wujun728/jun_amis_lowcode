package com.jun.plugin.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.common.*;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.service.RoleService;
import com.jun.plugin.system.service.UserService;
import com.jun.plugin.system.vo.UserVo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName: UserController Description: layui date: 2020/4/14 19:48
 *
 * 
 * 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * 加载用户数据
	 *
	 * @param userVo
	 * @return
	 */
	@GetMapping("loadAllUser")
	public Object loadAllUser(UserVo userVo) {
		DataGridView dataGridView = this.userService.queryAllUser(userVo);
		System.out.println(dataGridView);
		return dataGridView;
	}

	/**
	 * 添加用户
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("addUser")
	public ResultObj addUser(User user) {
		try {
			// 设置用户基本信息
			user.setSalt(MD5Utils.createUUID());
			user.setType(Constant.USER_TYPE_NORMAL);
			user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
			user.setAvailable(Constant.AVAILABLE_TRUE);
			user.setImgpath(Constant.DEFAULT_TITLE_IMAGE);
			// 进行保存用户
			this.userService.saveUser(user);
			// 设置用户默认的角色
			this.saveUserRole(user.getId(), Constant.DEFAULT_ROLE_NAME);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			return ResultObj.ADD_ERROR;
		}
	}

	@PostMapping("saveUserRole")
	public ResultObj saveUserRole(Integer uid, Integer[] rids) {
		try {
			this.userService.saveUserRole(uid, rids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}

	/**
	 * 修改
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("updateUser")
	public ResultObj updateUser(User user) {
		try {
			this.userService.updateUser(user);
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
	@PostMapping("deleteUser")
	public ResultObj deleteUser(Integer id) {
		try {
			this.userService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 查询最大排序码
	 *
	 * @return
	 */
	@GetMapping("queryUserMaxOrderNum")
	public Object queryUserMaxOrderNum() {
		return new DataGridView(this.userService.queryUserMaxOrderNum() + 1);
	}

	/**
	 * 查询当前登陆用户
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("getCurrentUser")
	public Object getCurrentUser(Integer id) {
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
		return new DataGridView(activeUser.getUser());
	}

	/**
	 * 重置密码123456
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("resetUserPwd")
	public ResultObj resetUserPwd(Integer id) {
		try {
			User user = new User();
			// 设置用户基本学习
			user.setId(id);
			user.setSalt(MD5Utils.createUUID());
			user.setType(Constant.USER_TYPE_NORMAL);
			user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
			// 只更新传入进去的
			this.userService.updateUser(user);
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			return ResultObj.RESET_ERROR;
		}

	}

}
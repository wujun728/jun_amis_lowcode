package com.ruoyi.project.system.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 个人信息 业务层处理
 * @author ruoyi
 */
@Service
public class ProfileService extends CommonService {

    @Autowired
    private PasswordService passwordService;

    /**
     * 修改用户基本资料
     * @param request HttpServletRequest对象
     * @return 结果
     */
	public int updateUserInfo(HttpServletRequest request) {
    	User user = ShiroUtils.getSysUser();
    	String user_name = RequestUtil.getValue(request, "user_name");
    	String phonenumber = RequestUtil.getValue(request, "phonenumber");
    	String email = RequestUtil.getValue(request, "email");
    	String sex = RequestUtil.getValue(request, "sex");

		//修改用户信息
		String sql = "update sys_user " +
					 "   set user_name = ?, email = ?, phonenumber = ?, sex = ?, " +
					 "   	 update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		return db.execute(sql, new Object[]{user_name, email, phonenumber, sex, user.getLoginName(), user.getUserId()});
	}

    /**
     * 修改头像
     * @param avatar 头像路径
     * @return 结果
     */
	public int updateUserAvatar(String avatar) {
    	User user = ShiroUtils.getSysUser();

		//修改用户信息
		String sql = "update sys_user " +
					 "   set avatar = ?, update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		return db.execute(sql, new Object[]{avatar, user.getLoginName(), user.getUserId()});
	}

    /**
     * 修改用户密码
     * @param user        用户信息
     * @param newPassword 新密码
     * @return 结果
     */
	public int resetUserPwd(User user, String newPassword) {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));

		//修改用户密码
		String sql = "update sys_user " +
					 "   set salt = ?, password = ?, pwd_update_date = sysdate(), update_by = ?, update_time = sysdate() " +
					 " where user_id = ?";
		return db.execute(sql, new Object[]{user.getSalt(), newPassword, user.getUserId(), user.getUserId()});
	}
}
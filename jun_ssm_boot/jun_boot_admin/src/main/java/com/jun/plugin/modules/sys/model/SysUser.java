package com.jun.plugin.modules.sys.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jun.plugin.common.annotation.Excel;
import com.jun.plugin.common.annotation.Excel.ColumnType;
import com.jun.plugin.common.annotation.Excel.Type;

@TableName("sys_user")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("user_id")
	@Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
	private Long userId;

	/**
	 * 用户名
	 */
	@TableField("username")
	@Excel(name = "用户名称")
	private String username;

	@TableField("password")
	private String password;

	/**
	 * 加密盐值
	 */
	@TableField("salt")
	private String salt;

	/**
	 * 邮箱
	 */
	@TableField("email")
	@Excel(name = "用户邮箱")
	private String email;

	/**
	 * 联系方式
	 */
	@TableField("phone")
	@Excel(name = "用户手机")
	private String phone;

	/**
	 * 年龄：1男2女
	 */
	@TableField("sex")
	@Excel(name = "用户性别", readConverterExp = "1=男,2=女")
	private Integer sex;

	/**
	 * 年龄
	 */
	@TableField("age")
	@Excel(name = "用户年龄")
	private Integer age;

	/**
	 * 用户状态：1有效; 0无效
	 */
	@TableField("status")
	@Excel(name = "账号状态", readConverterExp = "1=有效,0=无效")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	/**
	 * 最后登录时间
	 */
	@TableField("last_login_time")
	@Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
	private Date lastLoginTime;

	/**
	 * 登录ip
	 */
	@TableField(exist = false)
	private String loginIpAddress;

	/**
	 * 角色
	 */
	@TableField(exist = false)
	private List<SysRole> roles;

	/**
	 * 获取用户id
	 *
	 * @return user_id - 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 *
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId == null ? null : userId;
	}

	/**
	 * 获取用户名
	 *
	 * @return username - 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 *
	 * 重写获取盐值方法，自定义realm使用 Gets credentials salt.
	 *
	 * @return the credentials salt
	 */
	public String getCredentialsSalt() {
		return username + "nb666.net" + salt;
	}

	/**
	 * 获取加密盐值
	 *
	 * @return salt - 加密盐值
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 设置加密盐值
	 *
	 * @param salt 加密盐值
	 */
	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	/**
	 * 获取邮箱
	 *
	 * @return email - 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 *
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 获取联系方式
	 *
	 * @return phone - 联系方式
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系方式
	 *
	 * @param phone 联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * 获取年龄：1男2女
	 *
	 * @return sex - 年龄：1男2女
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置年龄：1男2女
	 *
	 * @param sex 年龄：1男2女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获取年龄
	 *
	 * @return age - 年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置年龄
	 *
	 * @param age 年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取用户状态：1有效; 0删除
	 *
	 * @return status - 用户状态：1有效; 0删除
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置用户状态：1有效; 0删除
	 *
	 * @param status 用户状态：1有效; 0删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取最后登录时间
	 *
	 * @return last_login_time - 最后登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 设置最后登录时间
	 *
	 * @param lastLoginTime 最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public boolean isAdmin() {
		return isAdmin(this.userId);
	}

	public static boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}
}
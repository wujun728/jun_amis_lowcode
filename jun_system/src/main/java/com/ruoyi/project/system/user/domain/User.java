package com.ruoyi.project.system.user.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 用户对象 sys_user
 * @author ruoyi
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@MapRow(column = "user_id", type = RowType.LONG)
	private Long userId;

	/** 部门ID */
	@MapRow(column = "dept_id", type = RowType.LONG)
	private Long deptId;

	/** 部门ID */
	@MapRow(column = "dept_name", type = RowType.STRING)
	private String deptName;

	/** 部门父ID */
	@MapRow(column = "parent_id", type = RowType.LONG)
	private Long parentId;

	/** 角色ID */
	@MapRow(column = "role_id", type = RowType.LONG)
	private Long roleId;

	/** 登录名称 */
	@MapRow(column = "login_name", type = RowType.STRING)
	private String loginName;

	/** 用户名称 */
	@MapRow(column = "user_name", type = RowType.STRING)
	private String userName;

    /** 用户类型 */
    @MapRow(column = "user_type", type = RowType.STRING)
    private String userType;

	/** 用户邮箱 */
	@MapRow(column = "email", type = RowType.STRING)
	private String email;

	/** 手机号码 */
	@MapRow(column = "phonenumber", type = RowType.STRING)
	private String phonenumber;

	/** 用户性别 */
	@MapRow(column = "sex", type = RowType.STRING)
    private String sex;

	/** 用户头像 */
	@MapRow(column = "avatar", type = RowType.STRING)
	private String avatar;

	/** 密码 */
	@MapRow(column = "password", type = RowType.STRING)
	private String password;

	/** 盐加密 */
	@MapRow(column = "salt", type = RowType.STRING)
	private String salt;

	/** 帐号状态（0正常 1停用） */
	@MapRow(column = "status", type = RowType.STRING)
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	@MapRow(column = "del_flag", type = RowType.STRING)
	private String delFlag;

	/** 最后登录IP */
	@MapRow(column = "login_ip", type = RowType.STRING)
	private String loginIp;

	/** 最后登录时间 */
	@MapRow(column = "login_date", type = RowType.DATE)
	private Date loginDate;

    /** 密码最后更新时间 */
    @MapRow(column = "pwd_update_date", type = RowType.DATE)
    private Date pwdUpdateDate;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@MapRow(column = "create_time", type = RowType.DATE)
	private Date createTime;

	/** 备注 */
	@MapRow(column = "remark", type = RowType.STRING)
	private String remark;

    /** 用户角色（角色编号逗号拼接） */
    @MapRow(column = "role_ids", type = RowType.STRING)
    private String roleIds;

    /** 用户岗位（岗位编号逗号拼接） */
    @MapRow(column = "post_ids", type = RowType.STRING)
    private String postIds;

	/** 角色集合 */
    private List<Role> roles;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isAdmin() {
		return isAdmin(this.userId);
	}

	public static boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@NotBlank(message = "登录账号不能为空")
	@Size(min = 0, max = 30, message = "登录账号长度不能超过30个字符")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

    @JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 生成随机盐
	 */
	public void randomSalt() {
		// 一个Byte占两个字节，此处生成的3字节，字符串长度为6
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		String hex = secureRandom.nextBytes(3).toHex();
		setSalt(hex);
	}

	public static String getRandomSalt() {
		// 一个Byte占两个字节，此处生成的3字节，字符串长度为6
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		return secureRandom.nextBytes(3).toHex();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

    public Date getPwdUpdateDate() {
        return pwdUpdateDate;
    }

    public void setPwdUpdateDate(Date pwdUpdateDate) {
        this.pwdUpdateDate = pwdUpdateDate;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPostIds() {
		return postIds;
	}

	public void setPostIds(String postIds) {
		this.postIds = postIds;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
                .append("deptId", getDeptId()).append("deptName", getDeptName()).append("loginName", getLoginName())
                .append("userName", getUserName()).append("userType", getUserType()).append("email", getEmail())
                .append("phonenumber", getPhonenumber()).append("sex", getSex()).append("avatar", getAvatar())
                .append("password", getPassword()).append("salt", getSalt()).append("status", getStatus())
                .append("delFlag", getDelFlag()).append("loginIp", getLoginIp()).append("loginDate", getLoginDate())
                .append("pwdUpdateDate", getPwdUpdateDate()).append("createBy", getCreateBy()).append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
                .append("roles", getRoles()).toString();
    }
}
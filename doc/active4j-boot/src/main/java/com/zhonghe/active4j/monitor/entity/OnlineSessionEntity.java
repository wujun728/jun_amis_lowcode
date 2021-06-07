package com.zhonghe.active4j.monitor.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhonghe.active4j.common.entity.BaseEntity;
import com.zhonghe.active4j.core.annotation.QueryField;
import com.zhonghe.active4j.core.model.QueryCondition;

import lombok.Getter;
import lombok.Setter;

/**
 * @title OnlineSessionEntity.java
 * @description 
		  在线用户实体
 * @time  2019年12月5日 下午5:22:45
 * @author 麻木神
 * @version 1.0
*/
@Getter
@Setter
@TableName("sys_online_session")
public class OnlineSessionEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 703695776531685689L;

	public static final String Status_Online = "1"; //在线
	public static final String Status_Offline = "0";//离线
	
	/**
	 * session id
	 */
	@TableField("SESSION_ID")
	private String sessionId;
	
	
	/**
	 * 用户ID
	 */
	@TableField("USER_ID")
	private String userId;
	
	
	/**
	 * 用户名
	 */
	@TableField("USER_NAME")
	@QueryField(queryColumn="USER_NAME", condition=QueryCondition.eq)
	private String userName;
	
	/**
	 * 名称
	 */
	@TableField("REAL_NAME")
	private String realName;
	
	/**
	 * 部门名称
	 */
	@TableField("DEPT_NAME")
	private String deptName;
	
	/**
	 * 用户头像
	 */
	@TableField("AVATAR")
    private String avatar;

    /**
     	* 登录IP
     */
	@TableField("HOST")
    private String host;

    /**
     * 浏览器
     */
	@TableField("BROWSER")
    private String browser;

    /**
     * 操作系统
     */
	@TableField("OS")
    private String os;
    
    /**
     * 状态    1：在线         0：离线
     */
	@TableField("STATUS")
	@QueryField(queryColumn="STATUS", condition=QueryCondition.eq)
    private String status;
    
    
    /**
     * session开始时间
     */
	@TableField("BEGIN_TIME")
	@QueryField(queryColumn="BEGIN_TIME", condition=QueryCondition.range)
    private Date beginTime;
    
    
    /**
     * session更新时间
     */
	@TableField("LAST_TIME")
    private Date lastTime;
	
}

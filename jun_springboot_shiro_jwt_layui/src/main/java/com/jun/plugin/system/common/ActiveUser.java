package com.jun.plugin.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.domain.User;

/**
 * ClassName: ActiveUser Description: layui date: 2020/4/14 20:10
 *
 * 
 * 
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser implements Serializable {
	/**
	 * 用户类
	 */
	private User user;
	/**
	 * 角色
	 */
	private List<String> roles;
	/**
	 * 权限
	 */
	private List<String> permissions;
}
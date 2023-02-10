package com.jun.plugin.system.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: ShiroPropeties Description: layui date: 2020/4/14 19:49
 *
 * 
 * 
 * @ shiro 配置文件类
 * 
 * @since JDK 1.8
 */
// 注入配置属性
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {
	/**
	 * 加密方式
	 */
	private String hashAlgorithmName = "md5";
	/**
	 * 散列次数
	 */
	private Integer hashIterations = 2;
	/**
	 * 登陆URL
	 */
	private String loginUrl;
	/**
	 * 没有授权的跳转url
	 */
	private String unauthorizedUrl;
	/**
	 * 放行url
	 */
	private String[] anonUrls;
	/**
	 * 退出url
	 */
	private String logoutUrl;
	/**
	 * 拦截url
	 */
	private String[] authcUrls;
}
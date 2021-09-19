package com.jun.plugin.system.controller;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.system.common.*;
import com.jun.plugin.system.domain.Loginfo;
import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.service.LoginfoService;
import com.jun.plugin.system.service.MenuService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * ClassName: LoginController Description: layui date: 2020/4/14 20:01
 *
 * 
 * 
 * @since JDK 1.8
 */
@Controller
@RequestMapping("api/login")
public class LoginController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginfoService loginfoService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 用户登陆
	 *
	 * @param loginname 传递来的登陆名称
	 * @param password  传递来打密码
	 * @param keyCode   传递来的验证码名称 key
	 * @param captcha   传递来的验证码
	 * @return 返回登陆信息
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public ResultObj doLogin(String loginname, String password, String keyCode, String captcha) {
		// 获取redis验证码
		ValueOperations<String, String> forValue = redisTemplate.opsForValue();
		String code = forValue.get(keyCode);
		if (null == code) {
			return new ResultObj(-1, "当前验证码过期请重新获取");
		} else {

			try {
				System.out.println(captcha);
				// 进行比较 不考虑大小写
				if (code.equalsIgnoreCase(captcha)) {
					// 获取主体
					Subject subject = SecurityUtils.getSubject();
					// 认证
					UsernamePasswordToken passwordToken = new UsernamePasswordToken(loginname, password);
					subject.login(passwordToken);
					// ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
					// 通过Shiro获取 Token sessionid = token
					String token = subject.getSession().getId().toString();
					/* 写入登陆日志开始 */
					Loginfo loginfo = new Loginfo();
					String remoteAddr = WebUtils.getHttpServletRequest().getRemoteAddr();
					ActiveUser active = (ActiveUser) subject.getPrincipal();
					loginfo.setLoginip(remoteAddr);
					loginfo.setLoginname(active.getUser().getName() + "-" + active.getUser().getLoginname());
					loginfo.setLogintime(new Date());
					loginfoService.save(loginfo);
					/* 写入登陆日志结束 */

					// 存储 前端登陆需要的信息
					Map<String, Object> map = new HashMap<>();
					map.put("token", token);
					map.put("permissions", active.getPermissions());
					map.put("username", active.getUser().getName());
					map.put("usertype", active.getUser().getType());
					System.err.println("TOKEN:" + token);
					return new ResultObj(200, "登陆成功", map);

				} else {
					return new ResultObj(-1, "验证码错误");
				}
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return new ResultObj(-1, "用户名或密码不正确");
			}

		}
	}

	/**
	 * 初始化 加载所有菜单[顶部菜单和左侧菜单]
	 *
	 * @return
	 */
	@RequestMapping("loadIndexMenu")
	@ResponseBody
	public Object loadIndexMenu() {
		// 1.思路: 需要判断当前是否为超级管理员 返回不同的菜单
		// 获取当前用户
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		User user = activeUser.getUser();
		if (null != user) {
			// 存储菜单
			List<Menu> menus = null;
			if (user.getType().equals(Constant.USER_TYPE_SUPER)) {
				// 超级管理员
				// 进行查询
				menus = menuService.queryAllMenuForList();
			} else {
				// 进行查询
				// 根据当前用户id 查询 当前用拥有的菜单
				menus = menuService.queryMenuForListByUserId(user.getId());
			}
			// 组装树
			List<MenuTreeNode> treeNodes = new ArrayList<>();
			for (Menu m : menus) {
				// 判断一下是否展开 前端需要的为Boolean类型而数据储存的为 int类型
				Boolean spread = m.getSpread() == Constant.SPREAD_TRUE ? true : false;
				// 每一次添加菜单
				treeNodes.add(new MenuTreeNode(m.getId(), m.getPid(), m.getTitle(), m.getHref(), m.getIcon(), spread,
						m.getTarget(), m.getTypecode()));
			}
			// 组装 子节点
			List<MenuTreeNode> build = MenuTreeNode.MenuTreeNodeBuild.build(treeNodes, 0);
			// 使用链式进行排序让后添加的菜单 放在后面去 固定系统和业务菜单前置
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			// 最后组装json类型
			for (MenuTreeNode node : build) {
				/*
				 * 组装类型 { "business":{ }, "system":{ } }
				 */
				map.put(node.getTypecode(), node);
			}
			return map;
		}
		return null;
	}

	/**
	 * 验证是否登陆过
	 *
	 * @return
	 */
	@RequestMapping("checkLogin")
	@ResponseBody
	public ResultObj checkLogin() {
		Subject subject = SecurityUtils.getSubject();
		// 判断是否认证成功
		if (subject.isAuthenticated()) {
			return ResultObj.IS_LOGIN;
		} else {
			return ResultObj.UN_LOGIN;
		}
	}

	/**
	 * 返回验证码 思路： 把验证码存入redis 登陆时候 在进行获取出来 进行相应的判断 接收的形式 key --- value
	 *
	 * @param response 返回出去的流
	 * @param codeKey  接收验证码的key
	 * @throws IOException
	 */
	@RequestMapping("captcha")
	public void captcha(HttpServletResponse response, String codeKey) throws IOException {
		// 定义验证码
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 38, 4, 2);
		String code = captcha.getCode();

		System.out.println(code);

		// 储存到redis
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(codeKey, code);
		// 设置 缓存时间 60秒
		opsForValue.getOperations().expire(codeKey, 60, TimeUnit.SECONDS);
		// 返回流
		captcha.write(response.getOutputStream());

	}

	@RequestMapping("logout")
	@ResponseBody
	public ResultObj logout(HttpServletRequest request) {

		// 清楚session
		HttpSession session = request.getSession();
		session.invalidate();

		// 退出认证
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		// 清理缓存
		String header = org.apache.shiro.web.util.WebUtils.toHttp(request).getHeader("TOKEN");
		// 删除redis缓存当中的 token
		redisTemplate.delete(header);

		return new ResultObj(200, "success");

	}

}
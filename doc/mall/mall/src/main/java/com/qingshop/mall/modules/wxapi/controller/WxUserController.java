package com.qingshop.mall.modules.wxapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.IpUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallOrder;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallOrderService;
import com.qingshop.mall.modules.mall.service.IMallUserService;
import com.qingshop.mall.modules.wxapi.service.UserTokenManager;
import com.qingshop.mall.modules.wxapi.vo.UserInfo;
import com.qingshop.mall.modules.wxapi.vo.WxLoginInfo;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import io.swagger.annotations.ApiOperation;

/**
 * 用户中心
 */
@RestController
@RequestMapping("/wx/user")
public class WxUserController extends BaseController {

	@Autowired
	private WxMaService wxService;

	@Autowired
	private IMallUserService mallUserService;

	@Autowired
	private IMallOrderService mallOrderService;

	/**
	 * 个人中心数据
	 * 
	 * @return 个人中心数据
	 */
	@ApiOperation(value = "个人中心数据", response = Rest.class)
	@GetMapping("/index")
	public Rest index(@LoginUser Long userId) {
		try {
			// 未授权
			if (userId == null) {
				return Rest.failure(-1, "账号未授权");
			}
			Map<String, Object> data = new HashMap<>();
			Map<String, Object> orderCount = new HashMap<>();
			MallUser user = mallUserService.getById(userId);
			QueryWrapper<MallOrder> userOrderEw = new QueryWrapper<MallOrder>();
			userOrderEw.eq("user_id", userId);
			List<MallOrder> userOrderList = mallOrderService.list(userOrderEw);
			List<MallOrder> payment = userOrderList.stream().filter(m -> m.getOrderStatus() == 1 && m.getPayStatus() == 0).collect(Collectors.toList());
			List<MallOrder> delivery = userOrderList.stream().filter(m -> m.getOrderStatus() == 1 && m.getPayStatus() == 1 && m.getShipStatus() == 0).collect(Collectors.toList());
			List<MallOrder> received = userOrderList.stream().filter(m -> m.getOrderStatus() == 1 && m.getPayStatus() == 1 && m.getShipStatus() == 1).collect(Collectors.toList());
			List<MallOrder> completed = userOrderList.stream().filter(m -> m.getPayStatus() == 1 && m.getShipStatus() == 2).collect(Collectors.toList());
			orderCount.put("payment", payment.size());
			orderCount.put("delivery", delivery.size());
			orderCount.put("received", received.size());
			orderCount.put("completed", completed.size());
			data.put("userInfo", user);
			data.put("orderCount", orderCount);
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("个人中心数据查询失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}

	/**
	 * 授权登录
	 * 
	 * @return 授权登录
	 */
	@ApiOperation(value = " 授权登录", response = Rest.class)
	@PostMapping("/login")
	public Rest login(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
		try {
			Map<String, Object> data = new HashMap<>();
			String code = wxLoginInfo.getCode();
			String sessionKey = null;
			String openId = null;
			try {
				WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
				sessionKey = result.getSessionKey();
				openId = result.getOpenid();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("获取微信openId失败！");
			}
			if (sessionKey == null || openId == null) {
				return Rest.failure();
			}
			MallUser user = mallUserService.getOne(new QueryWrapper<MallUser>().eq("weixin_openid", openId));
			if (user == null) {
				user = new MallUser();
				Date currentTime = new Date();
				UserInfo userInfo = wxLoginInfo.getUserInfo();
				user.setUserId(DistributedIdWorker.nextId());
				user.setAvatar(userInfo.getAvatarUrl());
				user.setGender(Integer.valueOf(userInfo.getGender()));
				user.setNickname(userInfo.getNickName());
				user.setUsername(userInfo.getNickName());
				user.setWeixinOpenid(openId);
				user.setStatus(0);
				user.setSessionKey(sessionKey);
				user.setLastLoginIp(IpUtils.getHostIp());
				user.setLastLoginTime(currentTime);
				user.setCreateTime(currentTime);
				user.setUpdateTime(currentTime);
				mallUserService.save(user);
			}
			String token = UserTokenManager.generateToken(user.getUserId());
			data.put("token", token);
			data.put("user_id", user.getUserId());
			return Rest.okData(data);
		} catch (Exception e) {
			logger.error("授权登录失败！", e.getMessage());
			System.out.println(e.getMessage());
		}
		return Rest.failure("服务器异常！");
	}
}

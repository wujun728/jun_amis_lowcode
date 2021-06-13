package com.qingshop.mall.modules.wxapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallAddress;
import com.qingshop.mall.modules.mall.service.IMallAddressService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/address")
public class WxAddressController extends BaseController {

	@Autowired
	private IMallAddressService mallAddressService;

	/**
	 * 用户收货地址列表
	 *
	 * @param userId 用户ID
	 * @return 收货地址列表
	 */
	@ApiOperation(value = "用户收货地址列表", response = Rest.class)
	@GetMapping("/list")
	public Rest list(@LoginUser Long userId) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		List<MallAddress> addressList = mallAddressService
				.list(new QueryWrapper<MallAddress>().eq("user_id", userId).orderByDesc("is_default"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		params.put("is_default", 1);
		MallAddress defaultAddress = mallAddressService.getOne(new QueryWrapper<MallAddress>().allEq(params));
		Map<String, Object> data = new HashMap<>();
		data.put("addressList", addressList);
		if (defaultAddress != null) {
			data.put("default_id", defaultAddress.getAddressId());
		}
		return Rest.okData(data);
	}

	/**
	 * 收货地址详情
	 *
	 * @param userId 用户ID
	 * @param id     收货地址ID
	 * @return 收货地址详情
	 */
	@ApiOperation(value = "收货地址详情", response = Rest.class)
	@GetMapping("/detail")
	public Rest detail(@LoginUser Long userId, String addressId) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		MallAddress address = mallAddressService.getById(addressId);
		if (address == null) {
			return Rest.failure("地址不能为空");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("detail", address);
		return Rest.okData(data);
	}

	private Rest validate(MallAddress address) {
		String name = address.getName();
		if (StringUtils.isEmpty(name)) {
			return Rest.failure("收货人不能为空");
		}

		// 测试收货手机号码是否正确
		String mobile = address.getPhone();
		if (StringUtils.isEmpty(mobile)) {
			return Rest.failure("手机号不能为空");
		}
		if (Pattern.matches("/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\\\d{8})$/", mobile)) {
			return Rest.failure("手机号码格式不正确");
		}

		String province = address.getProvince();
		if (StringUtils.isEmpty(province)) {
			return Rest.failure();
		}

		String city = address.getCity();
		if (StringUtils.isEmpty(city)) {
			return Rest.failure();
		}

		String region = address.getRegion();
		if (StringUtils.isEmpty(region)) {
			return Rest.failure();
		}

		String detailedAddress = address.getDetail();
		if (StringUtils.isEmpty(detailedAddress)) {
			return Rest.failure("详细地址不能为空");
		}

		return null;
	}

	/**
	 * 添加或更新收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址
	 * @return 添加或更新操作结果
	 */
	@ApiOperation(value = "添加或更新收货地址", response = Rest.class)
	@PostMapping("/add")
	public Rest add(@LoginUser Long userId, @RequestBody MallAddress address) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		Rest error = validate(address);
		if (error != null) {
			return error;
		}
		if (address.getAddressId() == null) {
			List<MallAddress> addressList = mallAddressService
					.list(new QueryWrapper<MallAddress>().eq("user_id", userId));
			if (addressList.size() > 0) {
				address.setIsDefault(0);
			} else {
				address.setIsDefault(1);
			}
			address.setAddressId(DistributedIdWorker.nextId());
			address.setUserId(userId);
			mallAddressService.save(address);
		} else {
			address.setUserId(userId);
			if (!mallAddressService.updateById(address)) {
				return Rest.failure();
			}
		}
		return Rest.okData(address.getAddressId());
	}

	/**
	 * 设置默认收货地址
	 *
	 * @param userId 用户ID
	 * @param body   json参数
	 * @return 设置默认收货地址
	 */
	@ApiOperation(value = "设置默认收货地址", response = Rest.class)
	@PostMapping("/setDefault")
	public Rest setDefault(@LoginUser Long userId, @RequestBody String body) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		JSONObject json = JSONObject.parseObject(body);
		String oldAddressId = json.getString("oldAddressId");
		String newAddressId = json.getString("newAddressId");
		if (StringUtils.isEmpty(newAddressId)) {
			return Rest.failure();
		}
		if (!StringUtils.isEmpty(oldAddressId)) {
			MallAddress oldAddress = new MallAddress();
			oldAddress.setAddressId(Long.valueOf(oldAddressId));
			oldAddress.setIsDefault(0);
			mallAddressService.updateById(oldAddress);
		}
		MallAddress newAddress = new MallAddress();
		newAddress.setAddressId(Long.valueOf(newAddressId));
		newAddress.setIsDefault(1);
		mallAddressService.updateById(newAddress);
		return Rest.ok();
	}

	/**
	 * 删除收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址
	 * @return 删除操作结果
	 */
	@ApiOperation(value = "删除收货地址", response = Rest.class)
	@PostMapping("/delete")
	public Rest delete(@LoginUser Long userId, @RequestBody MallAddress address) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		Long AddressId = address.getAddressId();
		if (AddressId == null) {
			return Rest.failure();
		}

		mallAddressService.removeById(AddressId);
		return Rest.ok();
	}
}

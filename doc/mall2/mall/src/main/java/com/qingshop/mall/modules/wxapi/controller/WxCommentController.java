package com.qingshop.mall.modules.wxapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.resolver.LoginUser;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallComment;
import com.qingshop.mall.modules.mall.entity.MallOrderDetail;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallCommentService;
import com.qingshop.mall.modules.mall.service.IMallOrderDetailService;
import com.qingshop.mall.modules.mall.service.IMallUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/comment")
public class WxCommentController extends BaseController {

	@Autowired
	private IMallOrderDetailService mallOrderDetailService;

	@Autowired
	private IMallCommentService mallCommentService;

	@Autowired
	private IMallUserService mallUserService;

	@ApiOperation(value = "订单商品评论详细", response = Rest.class)
	@GetMapping("/detail")
	public Rest detail(@LoginUser Long userId, Long orderId, Long goodsId) {
		if (userId == null) {
			Rest.failure(-1, "账号未授权登录");
		}
		QueryWrapper<MallOrderDetail> ew = new QueryWrapper<>();
		ew.eq("order_id", orderId);
		ew.eq("goods_id", goodsId);
		MallOrderDetail orderDetail = mallOrderDetailService.getOne(ew);
		orderDetail.setPicUrl(orderDetail.getPicUrl());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderGoods", orderDetail);
		return Rest.okData(data);
	}

	@ApiOperation(value = "添加商品评论", response = Rest.class)
	@PostMapping("/add")
	public Rest addComment(@LoginUser Long userId, @RequestBody MallComment mallComment) {
		if (userId == null) {
			return Rest.failure(-1, "账号未授权");
		}
		if (mallComment.getValueId() == null) {
			return Rest.failure();
		}
		if (StringUtils.isEmpty(mallComment.getContent())) {
			return Rest.failure("评论内容不能为空");
		}
		mallComment.setCommentId(DistributedIdWorker.nextId());
		mallComment.setUserId(userId);
		mallComment.setType(0);
		mallComment.setIsShow(0);
		if (mallCommentService.save(mallComment)) {
			return Rest.ok();
		} else {
			return Rest.failure();
		}
	}

	@ApiOperation(value = "商品评论列表", response = Rest.class)
	@GetMapping("/list")
	public Rest commentlist(Long valueId, Integer showType, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Page<MallComment> pages = this.getPage(page, limit);
		QueryWrapper<MallComment> ew = new QueryWrapper<>();
		ew.eq("value_id", valueId);
		ew.eq("is_show", 1);
		if (showType == 0) {
			ew.gt("star", 2);
		} else {
			ew.lt("star", 3);
		}
		ew.orderByDesc("create_time");
		IPage<MallComment> pageData = mallCommentService.page(pages, ew);
		List<MallComment> goodsCommentList = pageData.getRecords();
		List<Long> userIds = goodsCommentList.stream().map(m -> m.getUserId()).collect(Collectors.toList());
		Map<Long, MallUser> userMap = new HashMap<>();
		if (!StringUtils.isEmpty(userIds)) {
			List<MallUser> userList = (List<MallUser>) mallUserService.listByIds(userIds);
			userMap = userList.stream().collect(Collectors.toMap(MallUser::getUserId, a -> a, (k1, k2) -> k1));
		}
		List<Map<String, Object>> comments = new ArrayList<>();
		for (MallComment mallComment : goodsCommentList) {
			Map<String, Object> comment = new HashMap<>();
			comment.put("name", userMap.get(mallComment.getUserId()).getNickname());
			comment.put("avatarUrl", userMap.get(mallComment.getUserId()).getAvatar());
			comment.put("comment_rank", mallComment.getStar());
			comment.put("cont", mallComment.getContent());
			comment.put("time", mallComment.getCreateTime());
			comments.add(comment);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("comments", comments);
		data.put("page", page);
		return Rest.okData(data);
	}

	@ApiOperation(value = "商品评论列表统计", response = Rest.class)
	@GetMapping("/count")
	public Rest commentCount(Long valueId) {
		Integer goodCount = mallCommentService.count(new QueryWrapper<MallComment>().eq("value_id", valueId).gt("star", 2).eq("is_show", 1));
		Integer badCount = mallCommentService.count(new QueryWrapper<MallComment>().eq("value_id", valueId).lt("star", 3).eq("is_show", 1));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("goodCount", goodCount);
		data.put("badCount", badCount);
		return Rest.okData(data);
	}
}

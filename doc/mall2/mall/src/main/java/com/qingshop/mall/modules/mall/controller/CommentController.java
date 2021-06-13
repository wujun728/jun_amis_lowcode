package com.qingshop.mall.modules.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.mall.entity.MallComment;
import com.qingshop.mall.modules.mall.entity.MallGoods;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.service.IMallCommentService;
import com.qingshop.mall.modules.mall.service.IMallGoodsService;
import com.qingshop.mall.modules.mall.service.IMallUserService;

@Controller
@RequestMapping("/mall/comment")
public class CommentController extends BaseController {

	@Autowired
	private IMallCommentService mallCommentService;

	@Autowired
	private IMallUserService mallUserService;

	@Autowired
	private IMallGoodsService mallGoodsService;

	/**
	 * 获取商品下的所有评论
	 */
	@RequestMapping("/list")
	public String getComments() {
		return "mall/comment/list";
	}

	/**
	 * 获取商品下的所有评论
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest getCommentsListPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<MallComment> page = getPage(pageIndex, length);
		QueryWrapper<MallComment> ew = new QueryWrapper<MallComment>();
		if (StringUtils.isNotBlank(search)) {
			List<MallGoods> goods = mallGoodsService.list(new QueryWrapper<MallGoods>().like("good_name", search));
			List<Long> goodids = goods.stream().map(m -> m.getGoodsId()).collect(Collectors.toList());
			if (!StringUtils.isEmpty(goodids)) {
				ew.in("value_id", goodids);
			}
		}
		ew.orderByDesc("create_time");
		IPage<MallComment> pageData = mallCommentService.page(page, ew);
		List<MallComment> comments = pageData.getRecords();
		List<Long> userids = new ArrayList<>();
		List<Long> goodIds = new ArrayList<>();
		for (MallComment mallComment : comments) {
			Long userId = mallComment.getUserId();
			Long goodId = mallComment.getValueId();
			userids.add(userId);
			goodIds.add(goodId);
		}
		Map<Long, MallUser> userMap = new HashMap<>();
		if (!StringUtils.isEmpty(userids)) {
			List<MallUser> users = (List<MallUser>) mallUserService.listByIds(userids);
			userMap = users.stream().collect(Collectors.toMap(MallUser::getUserId, m -> m, (k1, k2) -> k1));
		}
		Map<Long, MallGoods> goodMap = new HashMap<>();
		if (!StringUtils.isEmpty(goodIds)) {
			List<MallGoods> goods = (List<MallGoods>) mallGoodsService.listByIds(goodIds);
			goodMap = goods.stream().collect(Collectors.toMap(MallGoods::getGoodsId, m -> m, (k1, k2) -> k1));
		}
		List<Map<String, Object>> commentList = new ArrayList<>();
		for (MallComment mallComment : comments) {
			Map<String, Object> comment = new HashMap<>();
			comment.put("picUrl", goodMap.get(mallComment.getValueId()).getPicUrl());
			comment.put("goodName", goodMap.get(mallComment.getValueId()).getGoodName());
			comment.put("commentId", mallComment.getCommentId());
			comment.put("name", userMap.get(mallComment.getUserId()).getNickname());
			comment.put("avatarUrl", userMap.get(mallComment.getUserId()).getAvatar());
			comment.put("star", mallComment.getStar());
			comment.put("content", mallComment.getContent());
			comment.put("isShow", mallComment.getIsShow());
			comment.put("createTime", mallComment.getCreateTime());
			commentList.add(comment);
		}
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", commentList);
		return resultMap;
	}

	/**
	 * 编辑
	 */
	@RequestMapping("/check/{id}")
	public String check(@PathVariable Long id, Model model) {
		MallComment comment = mallCommentService.getById(id);
		model.addAttribute("comment", comment);
		return "mall/comment/comentcheck";
	}

	/**
	 * 执行编辑
	 */
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(MallComment comment) {
		mallCommentService.updateById(comment);
		return Rest.ok();
	}

}

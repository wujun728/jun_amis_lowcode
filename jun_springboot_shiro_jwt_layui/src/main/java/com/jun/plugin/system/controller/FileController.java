package com.jun.plugin.system.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.common.upload.UploadService;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.service.UserService;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: FileController Description: layui date: 2020/4/16 20:18
 *
 * 
 * 
 * @since JDK 1.8 文件上传 管理
 */

@RestController
@RequestMapping("api/file")
public class FileController {
	@Autowired
	private UploadService uploadService;

	@Autowired
	private UserService userService;

	/**
	 * 上传文件 用户 储存到redis的
	 */
	@RequestMapping("uploadFile")
	public Object uploadFile(MultipartFile mf) {
		// 调用上传到FastDFS
		String path = this.uploadService.uploadImage(mf);
		Map<String, String> map = new HashMap<>();
		map.put("src", path);
		// 更新数据库
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
		User user = activeUser.getUser();
		user.setImgpath(path);
		// 更新用户
		userService.updateUser(user);
		return new DataGridView(map);
	}

	/**
	 * 上传文件 任意文件
	 */
	@RequestMapping("uploadGoodsFile")
	@ResponseBody
	public Object uploadGoodsFile(MultipartFile mf) {
		String path = this.uploadService.uploadImage(mf);

		Map<String, String> map = new HashMap<>();
		map.put("src", path);
		return new DataGridView(map);
	}

	@RequestMapping("uploadFileWang")
	@ResponseBody
	public Map<String, String> uploadFileWang(MultipartFile mf) {
		String path = this.uploadService.uploadImage(mf);
		Map<String, String> map = new HashMap<>();
		map.put("src", path);
		return map;
	}

}
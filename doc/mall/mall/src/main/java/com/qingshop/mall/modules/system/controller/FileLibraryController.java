package com.qingshop.mall.modules.system.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.oss.OssFactory;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.entity.SysUploadGroup;
import com.qingshop.mall.modules.system.service.ISysUploadFileService;
import com.qingshop.mall.modules.system.service.ISysUploadGroupService;

/**
 * 文件存储管理器
 */
@Controller
@RequestMapping("/library")
public class FileLibraryController extends BaseController {

	@Autowired
	private ISysUploadFileService uploadFileService;

	@Autowired
	private ISysUploadGroupService uploadGroupService;

	/**
	 * (列表页)
	 *
	 * @param uploadFile
	 * @param page
	 * @param pageSize
	 * @param search
	 * @return
	 */
	@PostMapping("/fileList")
	@ResponseBody
	public Map<String, Object> list(SysUploadFile uploadFile, @RequestParam Integer page, @RequestParam(defaultValue = "8") Integer pageSize, String search) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> resultData = new HashMap<String, Object>();
		List<SysUploadGroup> uploadGroupList = uploadGroupService.list();
		Page<SysUploadFile> pages = getPage(page, pageSize);
		QueryWrapper<SysUploadFile> ew = new QueryWrapper<SysUploadFile>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("original_name", search);
		}
		if (uploadFile.getGroupId() != -1L) {
			ew.eq("group_id", uploadFile.getGroupId());
		}
		ew.orderByDesc("create_time");
		// 查询分页
		IPage<SysUploadFile> pageData = uploadFileService.page(pages, ew);
		Map<String, Object> fileData = new HashMap<String, Object>();
		List<SysUploadFile> records = pageData.getRecords();
		long total = pageData.getTotal();
		fileData.put("total", total);
		fileData.put("per_page", pageSize);
		fileData.put("current_page", page);
		if(total%pageSize == 0) {
			fileData.put("last_page", total / pageSize);
		} else {
			fileData.put("last_page", total / pageSize + 1);
		}
		fileData.put("data", records);
		resultData.put("group_list", uploadGroupList);
		resultData.put("file_list", fileData);
		result.put("code", 200);
		result.put("msg", "success");
		result.put("data", resultData);
		return result;
	}

	/**
	 * (通用图片上传请求)
	 *
	 * @param file
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/image")
	@ResponseBody
	public Rest uploadImageFile(MultipartFile file, @RequestParam Long groupId) throws Exception {
		try {
			if (-1L == groupId) {
				groupId = 0L;
			}
			if (file.isEmpty()) {
				return Rest.failure("请选择文件！");
			} else {
				SysUploadFile fileResult = Objects.requireNonNull(OssFactory.init()).uploadFile(file, true);
				fileResult.setFileId(DistributedIdWorker.nextId());
				fileResult.setGroupId(groupId);
				uploadFileService.save(fileResult);
				Rest rest = Rest.ok();
				rest.put("originalName", fileResult.getOriginalName());
				rest.put("fileUrl", fileResult.getFileUrl());
				rest.put("fileId", fileResult.getFileId());
				return rest;
			}
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			return Rest.failure("服务器异常请联系管理员！");
		}
	}

	/**
	 * (删除图片)
	 *
	 * @param fileIds
	 * @return
	 */
	@PostMapping("/deleteFiles")
	@ResponseBody
	public Rest deleteFiles(String fileIds) {
		String[] ids = fileIds.split(",");
		List<SysUploadFile> files = (List<SysUploadFile>) uploadFileService.listByIds(Arrays.asList(ids));
        for(SysUploadFile file : files){
            Objects.requireNonNull(OssFactory.init(file.getOssType())).delete(file.getFilePath());
        }
        uploadFileService.removeByIds(Arrays.asList(ids));
		return Rest.ok("删除成功！");
	}

	/**
	 * (移动图片分类)
	 *
	 * @param fileIds
	 * @param groupId
	 * @return
	 */
	@PostMapping("/moveFiles")
	@ResponseBody
	public Rest moveFiles(String fileIds, Long groupId) {
		uploadFileService.updateUploadFileGroupId(fileIds, groupId);
		return Rest.ok("移动成功！");
	}

	/**
	 * (添加图片分组)
	 *
	 * @param uploadGroup
	 * @return
	 */
	@PostMapping("/addGroup")
	@ResponseBody
	public Rest addGroup(SysUploadGroup uploadGroup) {
		uploadGroup.setGroupId(DistributedIdWorker.nextId());
		uploadGroupService.save(uploadGroup);
		Rest rest = Rest.ok("添加成功");
		rest.put("groupId", uploadGroup.getGroupId());
		rest.put("groupName", uploadGroup.getGroupName());
		return rest;
	}

	/**
	 * (编辑图片组)
	 *
	 * @param uploadGroup
	 * @return
	 */
	@PostMapping("/editGroup")
	@ResponseBody
	public Rest editGroup(SysUploadGroup uploadGroup) {
		uploadGroupService.updateById(uploadGroup);
		Rest rest = Rest.ok("修改成功");
		rest.put("groupId", uploadGroup.getGroupId());
		rest.put("groupName", uploadGroup.getGroupName());
		return rest;
	}

	/**
	 * (删除图片组)
	 *
	 * @param uploadGroup
	 * @return
	 */
	@PostMapping("/deleteGroup")
	@ResponseBody
	public Rest deleteGroup(SysUploadGroup uploadGroup) {
		uploadGroupService.removeById(uploadGroup.getGroupId());
		Rest rest = Rest.ok();
		return rest;
	}
}

package com.qingshop.mall.modules.system.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.constant.Constants;
import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.PropertiesUtil;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.oss.OssFactory;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.service.ISysConfigService;
import com.qingshop.mall.modules.system.service.ISysUploadFileService;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

@Controller
@RequestMapping("/system/sysfile")
public class FileController extends BaseController {

	@Autowired
	private ISysUploadFileService uploadFileService;

	@Autowired
	private ISysConfigService configService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listFile")
	@RequestMapping("/list")
	public String list() {
		return "system/sysfile/list";
	}

	@RequiresPermissions("listFile")
	@PostMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysUploadFile> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysUploadFile> ew = new QueryWrapper<SysUploadFile>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("roleName", search);
		}
		ew.orderByDesc("createTime");
		IPage<SysUploadFile> pageData = uploadFileService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	@RequiresPermissions("addFile")
	@PostMapping("/upload")
	@ResponseBody
	public Rest uploadImageFile(MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				return Rest.failure("请选择文件！");
			} else {
				SysUploadFile fileResult = Objects.requireNonNull(OssFactory.init()).uploadFile(file, true);
				fileResult.setFileId(DistributedIdWorker.nextId());
				fileResult.setGroupId(0L);
				uploadFileService.save(fileResult);
				return Rest.ok("上传成功");
			}
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			return Rest.failure("服务器异常请联系管理员！");
		}
	}

	@RequiresPermissions("deleteFile")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(String id) {
		String[] idArry = id.split(",");
		List<SysUploadFile> files = (List<SysUploadFile>) uploadFileService.listByIds(Arrays.asList(idArry));
		for (SysUploadFile file : files) {
			Objects.requireNonNull(OssFactory.init(file.getOssType())).delete(file.getFilePath());
		}
		uploadFileService.removeByIds(Arrays.asList(idArry));
		return Rest.ok("删除文件成功");
	}

	@GetMapping(value = "/setStorage")
	public String setConfig(Model model) {
		String json = configService.selectAll();
		ConfigStorageVo configStorageVo = JsonUtils.jsonToBean(json, ConfigStorageVo.class);
		String workDir = PropertiesUtil.getString(Constants.WORK_DIR_KEY);
		model.addAttribute("workDir", workDir.endsWith(File.separator) ? workDir : workDir + File.separator);
		model.addAttribute("storageConfig", configStorageVo);
		return "system/sysfile/config";
	}

}

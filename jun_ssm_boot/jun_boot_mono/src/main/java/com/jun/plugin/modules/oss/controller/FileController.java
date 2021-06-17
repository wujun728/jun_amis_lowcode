package com.jun.plugin.modules.oss.controller;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.PageUtil;
import com.jun.plugin.common.util.PropertiesUtil;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.common.vo.PageResultVo;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.FileService;
import com.jun.plugin.modules.oss.service.OssFactory;
import com.jun.plugin.modules.sys.service.ConfigService;
import com.jun.plugin.modules.sys.vo.ConfigStorageVo;

@Controller
@RequestMapping("/sysfile")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private ConfigService configService;

	@PostMapping("/list")
	@ResponseBody
	public PageResultVo pageSysJob(SysFile vo, Integer limit, Integer offset) {
		try {
			PageHelper.startPage(PageUtil.getPageNo(limit, offset), limit);
			List<SysFile> fileList = fileService.listSysFile(vo);
			PageInfo<SysFile> pages = new PageInfo<>(fileList);
			return ResultUtil.table(fileList, pages.getTotal());
		} catch (Exception e) {
			logger.error(String.format("RoleController.loadRoles%s", e));
			throw e;
		}
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseVo<?> delete(String fileIdStr) {
		Long[] ids = Convert.toLongArray(",", fileIdStr);
		List<SysFile> files = fileService.listSysFileByIds(ids);
		fileService.deleteBatch(ids);
		for (SysFile file : files) {
			Objects.requireNonNull(OssFactory.init(file.getOssType())).delete(file.getFilePath());
		}
		return ResultUtil.success("删除文件成功");
	}

	@GetMapping(value = "/setStorage")
	public String setConfig(Model model) {
		String json = configService.getStorage();
		ConfigStorageVo configStorageVo = JSON.parseObject(json, ConfigStorageVo.class);
		String workDir = PropertiesUtil.getString(CoreConst.WORK_DIR_KEY);
		model.addAttribute("workDir", workDir.endsWith(File.separator) ? workDir : workDir + File.separator);
		model.addAttribute("storageConfig", configStorageVo);
		return "sysfile/config";
	}

	@RequestMapping("/common/download")
	public void commDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileUrl") String fileUrl, @RequestParam("fileName") String fileName) {
		ServletOutputStream out = null;
		InputStream inputStream = null;
		try {
			logger.info("下载中------fileUrl=" + fileUrl);
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3 * 1000);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			inputStream = conn.getInputStream();
			int len = 0;
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Cache-Control", "no-cache");
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
			logger.info("文件下载完成.....");
		} catch (Exception e) {
			logger.error("文件下载异常,e = {}", e);
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {

				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {

				}
			}
		}
	}

	@GetMapping("/common/fileViwe")
	public String fileViwe(ModelMap mmap, @RequestParam("fileUrl") String fileUrl, @RequestParam("extName") String extName) {
		mmap.put("fileUrl", fileUrl);
		mmap.put("extName", extName);
		return "sysfile/fileView";
	}

}

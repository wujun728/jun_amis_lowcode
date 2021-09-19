package com.jun.plugin.modules.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.FileUtils;
import com.jun.plugin.common.util.PropertiesUtil;

/**
 * 通用请求处理
 */
@Controller
public class CommonController {
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	/**
	 * 通用下载请求
	 * 
	 * @param fileName
	 *            文件名称
	 * @param delete
	 *            是否删除
	 */
	@GetMapping("common/download")
	public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
		try {
			if (!FileUtils.isValidFilename(fileName)) {
				throw new Exception("文件名称({" + fileName + "})非法，不允许下载。 ");
			}
			String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
			String filePath = PropertiesUtil.getString(CoreConst.WORK_DIR_KEY) + "/" + fileName;

			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			FileUtils.setAttachmentResponseHeader(response, realFileName);

			FileUtils.writeBytes(filePath, response.getOutputStream());
			if (delete) {
				FileUtils.deleteFile(filePath);
			}
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
	}

}

package com.jun.plugin.modules.oss.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.exception.OssException;
import com.jun.plugin.common.util.DateUtils;
import com.jun.plugin.common.util.FileUtils;
import com.jun.plugin.common.util.PropertiesUtil;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.OssTypeEnum;
import com.jun.plugin.modules.sys.vo.ConfigStorageVo;
import com.qiniu.util.IOUtils;

/**
 * 本地存储
 */
public class LocalOssService extends OssService {

	public LocalOssService(ConfigStorageVo config) {
		this.config = config;
	}

	@Override
	public SysFile upload(byte[] data, String path, boolean isPublic) {
		String date = DateUtils.dateTimeNow("yyyyMMdd");
		String realPath = getRealPath(path, date);
		try (FileOutputStream os = new FileOutputStream(realPath)) {
			FileCopyUtils.copy(data, os);
			String localDomain = config.getLocalDomain().endsWith("/") ? config.getLocalDomain() : config.getLocalDomain() + "/";
			String filePath = CoreConst.FILE_ + "/" + date + "/" + path;
			SysFile sysFile = new SysFile();
			sysFile.withFilePath(filePath).withFileFullPath(localDomain + filePath).withFileName(path).withFileType(getFileType(path)).withOssType(OssTypeEnum.LOCAL.getValue());
			return sysFile;
		} catch (Exception e) {
			throw new OssException("上传本地文件失败", e);
		}
	}

	@Override
	public SysFile upload(InputStream is, String path, boolean isPublic) {
		try {
			return upload(IOUtils.toByteArray(is), path, isPublic);
		} catch (IOException e) {
			throw new OssException("上传本地文件失败", e);
		}
	}

	@Override
	public SysFile uploadSuffix(byte[] data, String suffix, boolean isPublic) {
		return upload(data, getPath(suffix), isPublic);
	}

	@Override
	public SysFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic) {
		return upload(inputStream, getPath(suffix), isPublic);
	}

	@Override
	public void delete(String path) {
		FileUtils.deleteFile(PropertiesUtil.getString(CoreConst.WORK_DIR_KEY) + File.separator + path);
	}

	private String getPath(String suffix) {
		return UUIDUtil.generateShortUuid() + suffix;
	}

	private String getRealPath(String path, String pre) {
		String dir = PropertiesUtil.getString(CoreConst.WORK_DIR_KEY) + File.separator + CoreConst.FILE_ + File.separator + pre;
		if (!FileUtils.exists(dir)) {
			try {
				FileUtils.createDir(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dir + File.separator + path;
	}
}

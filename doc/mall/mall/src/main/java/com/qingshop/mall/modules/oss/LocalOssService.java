package com.qingshop.mall.modules.oss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.util.FileCopyUtils;

import com.qingshop.mall.common.constant.Constants;
import com.qingshop.mall.common.exception.OssException;
import com.qingshop.mall.common.utils.DateUtils;
import com.qingshop.mall.common.utils.FileUtil;
import com.qingshop.mall.common.utils.PropertiesUtil;
import com.qingshop.mall.common.utils.UUIDUtil;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;
import com.qiniu.util.IOUtils;

/**
 * 本地存储
 */
public class LocalOssService extends OssService {

	LocalOssService(ConfigStorageVo config) {
		this.config = config;
	}

	@Override
	public SysUploadFile upload(byte[] data, String path, boolean isPublic) {
		String date = DateUtils.dateTime();
		String realPath = getRealPath(path, date);
		try (FileOutputStream os = new FileOutputStream(realPath)) {
			FileCopyUtils.copy(data, os);
			String localDomain = config.getLocalDomain().endsWith("/") ? config.getLocalDomain() : config.getLocalDomain() + "/";
			String filePath = Constants.FILE_ + "/" + date + "/" + path;
			SysUploadFile sysFile = new SysUploadFile();
			sysFile.withFilePath(filePath).withFileFullPath(localDomain + filePath).withFileName(path).withFileType(getFileType(path)).withOssType(OssTypeEnum.LOCAL.getValue());
			return sysFile;
		} catch (Exception e) {
			throw new OssException("上传本地文件失败", e);
		}
	}

	@Override
	public SysUploadFile upload(InputStream is, String path, boolean isPublic) {
		try {
			return upload(IOUtils.toByteArray(is), path, isPublic);
		} catch (IOException e) {
			throw new OssException("上传本地文件失败", e);
		}
	}

	@Override
	public SysUploadFile uploadSuffix(byte[] data, String suffix, boolean isPublic) {
		return upload(data, getPath(suffix), isPublic);
	}

	@Override
	public SysUploadFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic) {
		return upload(inputStream, getPath(suffix), isPublic);
	}

	@Override
	public void delete(String path) {
		FileUtil.delete(Paths.get(PropertiesUtil.getString(Constants.WORK_DIR_KEY) + File.separator + path));
	}

	private String getPath(String suffix) {
		return UUIDUtil.generateShortUuid() + suffix;
	}

	private String getRealPath(String path, String pre) {
		String dir = PropertiesUtil.getString(Constants.WORK_DIR_KEY) + File.separator + Constants.FILE_ + File.separator + pre;
		if (!FileUtil.exists(dir)) {
			try {
				FileUtil.createDir(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dir + File.separator + path;
	}
}

package com.jun.plugin.modules.oss.service.impl;

import java.io.IOException;
import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.jun.plugin.common.exception.OssException;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.OssTypeEnum;
import com.jun.plugin.modules.sys.vo.ConfigStorageVo;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Region;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;

/**
 * 七牛云存储
 */
public class QiniuOssService extends OssService {

	private UploadManager uploadManager;

	private BucketManager bucketManager;

	private String token;

	public QiniuOssService(ConfigStorageVo config) {
		this.config = config;

		// 初始化
		init();
	}

	private void init() {
		Configuration cfg = new Configuration(Region.autoRegion());
		Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
		uploadManager = new UploadManager(cfg);
		bucketManager = new BucketManager(auth, cfg);
		token = auth.uploadToken(config.getQiniuBucketName());
	}

	@Override
	public SysFile upload(byte[] data, String path, boolean isPublic) {
		try {
			Response res = uploadManager.put(data, path, token);
			if (!res.isOK()) {
				throw new RuntimeException("上传七牛出错：" + res.toString());
			}
			DefaultPutRet putRet = JSON.parseObject(res.bodyString(), DefaultPutRet.class);
			SysFile sysFile = new SysFile();
			sysFile.withFilePath(path).withFileFullPath(config.getQiniuDomain() + "/" + path).withFileName(getFileName(path)).withFileType(getFileType(path)).withFileHash(putRet.hash).withOssType(OssTypeEnum.QINIU.getValue());
			return sysFile;
		} catch (Exception e) {
			throw new OssException("上传文件失败，请核对七牛配置信息", e);
		}
	}

	@Override
	public SysFile upload(InputStream inputStream, String path, boolean isPublic) {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			return this.upload(data, path, isPublic);
		} catch (IOException e) {
			throw new OssException("上传文件失败", e);
		}
	}

	@Override
	public SysFile uploadSuffix(byte[] data, String suffix, boolean isPublic) {
		return upload(data, getPath(config.getQiniuPrefix(), suffix), isPublic);
	}

	@Override
	public SysFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic) {
		return upload(inputStream, getPath(config.getQiniuPrefix(), suffix), isPublic);
	}

	@Override
	public void delete(String path) {
		try {
			bucketManager.delete(config.getQiniuBucketName(), path);
		} catch (QiniuException e) {
			throw new OssException("删除文件失败", e);
		}
	}
}

package com.jun.plugin.modules.oss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.jun.plugin.common.exception.OssException;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.OssTypeEnum;
import com.jun.plugin.modules.sys.vo.ConfigStorageVo;

/**
 * 阿里云存储
 */
public class AliyunOssService extends OssService {
	
	private OSSClient client;

	public AliyunOssService(ConfigStorageVo config) {
		this.config = config;
		// 初始化
		init();
	}

	private void init() {
		client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(), config.getAliyunAccessKeySecret());
	}

	@Override
	public SysFile upload(byte[] data, String path, boolean isPublic) {
		return upload(new ByteArrayInputStream(data), path, isPublic);
	}

	@Override
	public SysFile upload(InputStream inputStream, String path, boolean isPublic) {
		try {
			if (isPublic) {
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
				client.putObject(config.getAliyunBucketName(), path, inputStream, objectMetadata);
			} else {
				client.putObject(config.getAliyunBucketName(), path, inputStream);
			}
			SysFile sysFile = new SysFile();
			sysFile.withFilePath(path).withFileFullPath(config.getAliyunDomain() + "/" + path).withFileName(getFileName(path)).withFileType(getFileType(path)).withOssType(OssTypeEnum.ALIYUN.getValue());
			return sysFile;
		} catch (Exception e) {
			throw new OssException("上传文件失败，请检查配置信息", e);
		} finally {
			client.shutdown();
		}
	}

	@Override
	public SysFile uploadSuffix(byte[] data, String suffix, boolean isPublic) {
		return upload(data, getPath(config.getAliyunPrefix(), suffix), isPublic);
	}

	@Override
	public SysFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic) {
		return upload(inputStream, getPath(config.getAliyunPrefix(), suffix), isPublic);
	}

	@Override
	public void delete(String path) {
		client.deleteObject(config.getAliyunBucketName(), path);
		client.shutdown();
	}

}

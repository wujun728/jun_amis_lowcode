package com.qingshop.mall.modules.oss;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qingshop.mall.common.exception.OssException;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 腾讯云存储
 */
public class QcloudOssService extends OssService {
    private COSClient client;

    QcloudOssService(ConfigStorageVo config){
        this.config = config;
        init();
    }

    private void init(){
        COSCredentials credentials = new BasicCOSCredentials(config.getQcloudSecretId(),
                config.getQcloudSecretKey());
        Region region = new Region(config.getQcloudRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        client = new COSClient(credentials, clientConfig);
    }

    @Override
    public SysUploadFile upload(byte[] data, String path, boolean isPublic) {
        return upload(new ByteArrayInputStream(data), path, isPublic);
    }

    @Override
    public SysUploadFile upload(InputStream inputStream, String path, boolean isPublic) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getQcloudBucketName(), path, inputStream, objectMetadata);
            if(isPublic){
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            }
            client.putObject(putObjectRequest);
            SysUploadFile sysFile = new SysUploadFile();
            sysFile.withFilePath(path)
                    .withFileFullPath(config.getQcloudDomain() + "/" + path)
                    .withFileName(getFileName(path))
                    .withFileType(getFileType(path))
                    .withOssType(OssTypeEnum.QCLOUD.getValue());
            return sysFile;
        } catch (Exception e) {
            throw new OssException("文件上传失败，" + e.getMessage());
        } finally {
            client.shutdown();
        }

    }

    @Override
    public SysUploadFile uploadSuffix(byte[] data, String suffix, boolean isPublic) {
        return upload(data, getPath(config.getQcloudPrefix(), suffix), isPublic);
    }

    @Override
    public SysUploadFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic) {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix), isPublic);
    }

    @Override
    public void delete(String path) {
        client.deleteObject(config.getQcloudBucketName(), path);
    }
}

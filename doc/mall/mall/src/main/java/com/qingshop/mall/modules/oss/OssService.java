package com.qingshop.mall.modules.oss;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSException;
import com.qingshop.mall.common.utils.DateUtils;
import com.qingshop.mall.common.utils.UUIDUtil;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.vo.ConfigStorageVo;

/**
 * 云存储公共服务类
 */
public abstract class OssService {
    /** 云存储配置信息 */
    ConfigStorageVo config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUIDUtil.generateShortUuid();
        //文件路径
        String path = DateUtils.dateTime() + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            if (prefix.endsWith("/")){
                path = prefix +  path;
            }else{
                path = prefix + "/" + path;
            }

        }

        return path + suffix;
    }

    /**
     * 获取文件名
     */
    String getFileName(String path){
        return path.substring(path.lastIndexOf("/")+1);
    }

    /**
     * 获取文件类型
     */
    String getFileType(String path){
        return path.substring(path.lastIndexOf(".")).toLowerCase();
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    public abstract SysUploadFile upload(byte[] data, String path, boolean isPublic);

    /**
     * 文件上传
     * @param data     文件字节数组
     * @param suffix   后缀
     * @return         返回http地址
     */
    public abstract SysUploadFile uploadSuffix(byte[] data, String suffix, boolean isPublic);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    public abstract SysUploadFile upload(InputStream inputStream, String path, boolean isPublic);

    /**
     * 文件上传
     * @param inputStream  字节流
     * @param suffix       后缀
     * @return             返回http地址
     */
    public abstract SysUploadFile uploadSuffix(InputStream inputStream, String suffix, boolean isPublic);

    /**
     * 删除文件
     * @param path        filePath
     */
    public abstract void delete(String path);

    /**
     * 文件上传
     * @param file          文件
     * @param isPublic     是否公开
     * @return              返回http地址
     */
    public SysUploadFile uploadFile(MultipartFile file, boolean isPublic){
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        try {
            byte[] bytes = file.getBytes();
            SysUploadFile sysFile = uploadSuffix(bytes, suffix, isPublic);
            sysFile.withOriginalName(file.getOriginalFilename())
                    .withFileSize(Long.valueOf(file.getSize()))
                    .withFileHash(DigestUtils.md5DigestAsHex(bytes));
            return sysFile;
        } catch (IOException e) {
            throw new OSSException("上传失败",e);
        }
    };

}

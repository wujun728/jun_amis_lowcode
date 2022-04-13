package com.element.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public final class UploadUtil {

    public static void upload(MultipartFile file, String fileName, String basePath, String prefix) throws Exception {
        File path = new File(basePath + prefix);
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            File newfile = new File(basePath + prefix + fileName);
            file.transferTo(newfile);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void uploadWithPath(MultipartFile file, String fileName, String basePath) throws Exception {
        File newfile = new File(basePath);
        if (!newfile.exists()) {
            newfile.mkdirs();
        }
        try {
            File sfile = new File(basePath + File.separator + fileName);
            file.transferTo(sfile);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 判断文件大小
     *
     * @param :multipartFile:上传的文件
     * @param size:                限制大小
     * @param unit:限制单位（B,K,M,G)
     * @return boolean:是否大于
     */
    public static boolean checkFileSize(MultipartFile multipartFile, int size, String unit) {
        long len = multipartFile.getSize();// 上传文件的大小, 单位为字节.
        // 准备接收换算后文件大小的容器
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        // 如果上传文件大于限定的容量
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}

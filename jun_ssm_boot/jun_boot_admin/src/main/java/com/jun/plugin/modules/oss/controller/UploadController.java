package com.jun.plugin.modules.oss.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.jun.plugin.common.exception.HtException;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.FileService;
import com.jun.plugin.modules.oss.service.OssFactory;

/**
 * 上传接口控制器
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseVo<?> upload(@RequestParam MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new HtException("文件不能为空");
        }
        try {
            SysFile sysFile = Objects.requireNonNull(OssFactory.init()).uploadFile(file, true);
            sysFile.setFileId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
            fileService.save(sysFile);
            return ResultUtil.success("上传成功", sysFile);
        }catch (Exception e) {
            logger.error(String.format("UploadController.upload%s", e));
            return ResultUtil.error("上传失败",file.getOriginalFilename());
        }
    }

    @PostMapping("/uploadForEditor")
    public String uploadForEditor(@RequestParam("img") List<MultipartFile> list){
        if (list == null || list.isEmpty()) {
            throw new HtException("文件不能为空");
        }
        List<String> urlList= new ArrayList<>();
        for(MultipartFile file : list){
            try {
                SysFile sysFile = Objects.requireNonNull(OssFactory.init()).uploadFile(file, true);
                String path = sysFile.getFileFullPath();
                fileService.save(sysFile);
                if(StringUtils.isNotBlank(path)){
                    urlList.add(path);
                }else{
                    return  "{\"errno\":-1,\"data\":[]}";
                }
            } catch (Exception e) {
                logger.error(String.format("UploadController.uploadForEditor%s", e));
                return  "{\"errno\":-1,\"data\":[]}";
            }
        }
        return  "{\"errno\":0,\"data\":"+ JSONUtils.toJSONString(urlList)+"}";

    }

}

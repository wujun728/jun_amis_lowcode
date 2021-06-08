package com.ifast.business.oss.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifast.business.oss.dao.FileDao;
import com.ifast.business.oss.domain.FileDO;
import com.ifast.business.oss.service.FileService;
import com.ifast.common.IFastProperties;
import com.ifast.common.base.CoreServiceImpl;
import com.ifast.common.support.oss.UploadServer;
import com.ifast.common.utils.FileType;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
public class FileServiceImpl extends CoreServiceImpl<FileDao, FileDO> implements FileService {

    @Autowired
    private IFastProperties ifastConfig;
    @Autowired
    private UploadServer uploader;

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        //处理浏览器文件名获取兼容问题
        if(fileName == null) {
            fileName =  "";
        } else {
            int unixSep = fileName.lastIndexOf("/");
            int winSep = fileName.lastIndexOf("\\");
            int pos = winSep > unixSep?winSep:unixSep;
            fileName= pos != -1?fileName.substring(pos + 1):fileName;
        }
//        fileName = fileName.substring(0, fileName.indexOf(".")) + "-" + System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));
//        fileName = ifastConfig.getProjectName() + "/" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_8)
//                + "/" + fileName;
        String url = uploader.upload(uploadBytes, fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), url, new Date());
//        super.insert(sysFile);
        super.save(sysFile);
        return url;
    }
}

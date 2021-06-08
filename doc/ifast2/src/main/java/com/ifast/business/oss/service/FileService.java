package com.ifast.business.oss.service;

import com.ifast.business.oss.domain.FileDO;
import com.ifast.common.base.CoreService;

/**
 * <pre>
 * 文件上传
 * </pre>
 * <small> 2018年4月6日 | Aron</small>
 */
public interface FileService extends CoreService<FileDO> {

    /**
     * <pre>
     * 上传文件，默认路径为 
     *      http:// + 七牛默认分配的域名 + / + 项目名 + / + 日期 + / + 文件名 + "-" + 时间戳 + "." + 后缀
     *      如：http://p6r7ke2jc.bkt.clouddn.com/ifast/20180406/cat001-123412412431.jpeg
     * </pre>
     * 
     * <small> 2018年4月6日 | Aron</small>
     * 
     * @param uploadBytes 文件字节数组
     * @param fileName 简单文件名，带后缀，如：mycat.png
     * @return
     */
    String upload(byte[] uploadBytes, String fileName);

}

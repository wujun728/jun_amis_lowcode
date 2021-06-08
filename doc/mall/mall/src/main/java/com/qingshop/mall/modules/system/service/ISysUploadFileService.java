package com.qingshop.mall.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysUploadFile;

/**
 * 文件存储表 服务类
 */
public interface ISysUploadFileService extends IService<SysUploadFile> {

	int updateUploadFileGroupId(String fileIds, Long groupId);

}

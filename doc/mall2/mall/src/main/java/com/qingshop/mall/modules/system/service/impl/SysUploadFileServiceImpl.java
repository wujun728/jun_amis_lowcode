package com.qingshop.mall.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.text.Convert;
import com.qingshop.mall.modules.system.entity.SysUploadFile;
import com.qingshop.mall.modules.system.mapper.SysUploadFileMapper;
import com.qingshop.mall.modules.system.service.ISysUploadFileService;

/**
 * 文件存储表 服务实现类
 */
@Service
public class SysUploadFileServiceImpl extends ServiceImpl<SysUploadFileMapper, SysUploadFile> implements ISysUploadFileService {

	@Override
	public int updateUploadFileGroupId(String fileIds, Long groupId) {
		return baseMapper.updateUploadFileGroupId(Convert.toStrArray(fileIds), groupId);

	}

}

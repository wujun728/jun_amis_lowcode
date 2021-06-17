package com.jun.plugin.modules.oss.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.modules.oss.mapper.SysFileMapper;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	
	@Autowired
	private SysFileMapper sysFileMapper;

	@Override
	public List<SysFile> listSysFile(SysFile vo) {
		QueryWrapper<SysFile> queryWrapper = new QueryWrapper<SysFile>();
		if (StringUtils.isNotEmpty(vo.getOriginalName())) {
			queryWrapper.like("original_name", vo.getOriginalName());
		}
		if (vo.getOssType() != null) {
			queryWrapper.eq("oss_type", vo.getOssType());
		}
		queryWrapper.orderByDesc("create_time");
		return sysFileMapper.selectList(queryWrapper);
	}

	@Override
	public void save(SysFile file) {
		Date date = new Date();
		file.setUpdateTime(date);
		file.withCreateTime(date).withStatus(CoreConst.STATUS_VALID);
		sysFileMapper.insert(file);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		QueryWrapper<SysFile> queryWrapper = new QueryWrapper<SysFile>();
		queryWrapper.in("file_id", Arrays.asList(ids));
		sysFileMapper.delete(queryWrapper);
	}

	@Override
	public List<SysFile> listSysFileByIds(Long[] ids) {
		QueryWrapper<SysFile> queryWrapper = new QueryWrapper<SysFile>();
		queryWrapper.in("fileId", Arrays.asList(ids));
		return sysFileMapper.selectList(queryWrapper);
	}
}

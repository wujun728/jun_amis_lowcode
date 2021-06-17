package com.jun.plugin.modules.oss.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.modules.oss.mapper.SysFileMapper;
import com.jun.plugin.modules.oss.model.SysFile;
import com.jun.plugin.modules.oss.service.FileService;

import tk.mybatis.mapper.entity.Example;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private SysFileMapper sysFileMapper;

	@Override
	public List<SysFile> listSysFile(SysFile vo) {
		Example example = new Example(SysFile.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(vo.getOriginalName())) {
			criteria.andLike("originalName", "%" + vo.getOriginalName() + "%");
		}
		if (vo.getOssType() != null) {
			criteria.andEqualTo("ossType", vo.getOssType());
		}
		example.orderBy("createTime").desc();
		return sysFileMapper.selectByExample(example);
	}

	@Override
	public void save(SysFile file) {
		Date date = new Date();
		file.setUpdateTime(date);
		file.withCreateTime(date).withStatus(CoreConst.STATUS_VALID);
		sysFileMapper.insertSelective(file);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		Example example = new Example(SysFile.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("fileId", Arrays.asList(ids));
		sysFileMapper.deleteByExample(example);
	}

	@Override
	public List<SysFile> listSysFileByIds(Long[] ids) {
		Example example = new Example(SysFile.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("fileId", Arrays.asList(ids));
		return sysFileMapper.selectByExample(example);
	}
}

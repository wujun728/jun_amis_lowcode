package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Systemlog;
import com.jun.plugin.system.mapper.SystemlogMapper;
import com.jun.plugin.system.service.SystemlogService;
import com.jun.plugin.system.vo.SystemlogVo;

/**
 * ClassName: SystemlogServiceImpl Description: layui date: 2020/4/15 18:09
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class SystemlogServiceImpl extends ServiceImpl<SystemlogMapper, Systemlog> implements SystemlogService {

	@Autowired
	private SystemlogMapper systemlogMapper;

	@Override
	public DataGridView queryAllSystemLog(SystemlogVo systemlogVo) {
		IPage<Systemlog> page = new Page<>(systemlogVo.getPage(), systemlogVo.getLimit());
		QueryWrapper<Systemlog> wrapper = new QueryWrapper<>();
		wrapper.like(StringUtils.isNotBlank(systemlogVo.getThisName()), "thisName", systemlogVo.getThisName());
		wrapper.like(StringUtils.isNotBlank(systemlogVo.getIp()), "ip", systemlogVo.getIp());
		wrapper.like(StringUtils.isNotBlank(systemlogVo.getAddress()), "address", systemlogVo.getAddress());
		wrapper.ge(systemlogVo.getStartTime() != null, "optime", systemlogVo.getStartTime());
		wrapper.le(systemlogVo.getEndTime() != null, "optime", systemlogVo.getEndTime());
		wrapper.orderByDesc("optime");
		this.systemlogMapper.selectPage(page, wrapper);

		return new DataGridView(page.getTotal(), page.getRecords());
	}
}

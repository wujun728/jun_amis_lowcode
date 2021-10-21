package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Loginfo;
import com.jun.plugin.system.mapper.LoginfoMapper;
import com.jun.plugin.system.service.LoginfoService;
import com.jun.plugin.system.vo.LoginfoVo;

/**
 * ClassName: LoginfoServiceImpl Description: layui date: 2020/4/15 19:10
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

	@Autowired
	private LoginfoMapper loginfoMapper;

	/**
	 * 查询全部日志
	 *
	 * @param loginfoVo 条件
	 * @return
	 */
	@Override
	public DataGridView queryAllLoginfo(LoginfoVo loginfoVo) {
		// mq 分页
		IPage<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());

		QueryWrapper<Loginfo> qw = new QueryWrapper<>();
		qw.like(StringUtils.isNoneBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
		qw.like(StringUtils.isNoneBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
		// 大于等于
		qw.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
		// 小于等于
		qw.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
		// 排序
		qw.orderByDesc("logintime");
		// 查询数据
		this.loginfoMapper.selectPage(page, qw);
		// 返回组装数据
		return new DataGridView(page.getTotal(), page.getRecords());
	}
}

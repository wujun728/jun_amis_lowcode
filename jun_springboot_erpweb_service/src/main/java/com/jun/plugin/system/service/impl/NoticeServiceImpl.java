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
import com.jun.plugin.system.domain.Notice;
import com.jun.plugin.system.mapper.NoticeMapper;
import com.jun.plugin.system.service.NoticeService;
import com.jun.plugin.system.vo.NoticeVo;

/**
 * ClassName: NoticeServiceImpl Description: layui date: 2020/4/16 15:51
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * 查询公告 高级查询
	 *
	 * @param noticeVo
	 * @return
	 */
	@Override
	public DataGridView queryAllNotice(NoticeVo noticeVo) {
		// 设置条件
		IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
		QueryWrapper<Notice> qw = new QueryWrapper<>();
		qw.like(StringUtils.isNoneBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
		qw.like(StringUtils.isNotBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
		qw.ge(noticeVo.getStartTime() != null, "createtime", noticeVo.getStartTime());
		qw.le(noticeVo.getStartTime() != null, "createtime", noticeVo.getEndTime());
		qw.orderByDesc("createtime");
		// 查询数据
		this.noticeMapper.selectPage(page, qw);
		// 返回数据
		return new DataGridView(page.getTotal(), page.getRecords());
	}
}

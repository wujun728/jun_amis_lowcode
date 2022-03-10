package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Notice;
import com.jun.plugin.system.vo.NoticeVo;

/**
 * ClassName: NoticeService Description: layui date: 2020/4/16 15:51
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface NoticeService extends IService<Notice> {

	/**
	 * 获取公共 带高级查询
	 *
	 * @param noticeVo
	 * @return
	 */
	DataGridView queryAllNotice(NoticeVo noticeVo);

}

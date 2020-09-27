package com.itstyle.es.log.service;
import com.itstyle.es.log.entity.Pages;
import com.itstyle.es.log.entity.SysLogs;


public interface LogService {
    /**
	 * 新增日志
	 * @param log
	 * @return
	 */
	void saveLog(SysLogs log);
    
    /**
     * 搜索词搜索，分页返回日志信息
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return JSON
     */
    Pages<SysLogs> searchLogPage(Integer pageNumber, Integer pageSize, Integer platFrom, String searchContent);
}

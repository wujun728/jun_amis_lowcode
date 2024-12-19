package com.ruoyi.project.monitor.job.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.monitor.job.domain.JobLog;

import cn.hutool.core.util.StrUtil;

/**
 * 定时任务调度日志信息 服务层
 * @author ruoyi
 */
@Service
public class JobLogService extends CommonService {

    /**
     * 获取quartz调度器日志的计划任务
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
	public TableDataInfo selectJobLogList(HttpServletRequest request, boolean pagination) {
		String job_name = RequestUtil.getValue(request, "job_name");
    	String job_group = RequestUtil.getValue(request, "job_group");
    	String status = RequestUtil.getValue(request, "status");
    	String invoke_target = RequestUtil.getValue(request, "invoke_target");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select job_log_id, job_name, job_group, invoke_target, job_message, status, exception_info, create_time" +
				   "  from sys_job_log where 1 = 1 ");

    	if(StrUtil.isNotBlank(job_name)) {
    		sql.append(" and job_name like concat('%', ?, '%') ");
    		paramList.add(job_name);
    	}
    	if(StrUtil.isNotBlank(job_group)) {
    		sql.append(" and job_group = ? ");
    		paramList.add(job_group);
    	}
    	if(StrUtil.isNotBlank(invoke_target)) {
    		sql.append(" and invoke_target like concat('%', ?, '%') ");
    		paramList.add(invoke_target);
    	}
    	if(StrUtil.isNotBlank(status)) {
    		sql.append(" and status = ? ");
    		paramList.add(status);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
	}

    /**
     * 通过调度任务日志ID查询调度信息
     * @param jobId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
	public JobLog selectJobLogById(String job_log_id) {
		String sql = "select job_log_id, job_name, job_group, invoke_target, job_message, status, exception_info, create_time "+
					 "  from sys_job_log where job_log_id = ?";
		return db.queryForObject(sql, new Object[]{job_log_id}, JobLog.class);
	}

    /**
     * 新增任务日志
     * @param jobLog 调度日志信息
     */
	public void addJobLog(JobLog jobLog) {
		String sql = "insert into sys_job_log(job_name, job_group, invoke_target, job_message, "+
					 "status, exception_info, create_time) values(?, ?, ?, ?, ?, ?, sysdate()) ";
		db.execute(sql, new String[]{jobLog.getJobName(), jobLog.getJobGroup(), jobLog.getInvokeTarget(),
				jobLog.getJobMessage(), jobLog.getStatus(), jobLog.getExceptionInfo()});
	}

    /**
     * 批量删除调度日志信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobLogByIds(String ids) {
    	List<String> paramList = new ArrayList<String>();
    	String sql = "delete from sys_job_log where job_log_id in ("+SqlUtil.rebuildInSql(ids, paramList)+")";
		return db.execute(sql, paramList.toArray());
	}

    /**
     * 清空任务日志
     */
	public void cleanJobLog() {
		String sql = "truncate table sys_job_log";
		db.execute(sql, null);
	}
}
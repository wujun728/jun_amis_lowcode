package com.ruoyi.project.monitor.job.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.util.CronUtils;
import com.ruoyi.project.monitor.job.util.ScheduleUtils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

/**
 * 定时任务调度信息 服务层
 * @author ruoyi
 */
@Service
public class JobService extends CommonService {
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
	public void init() throws SchedulerException, TaskException {
        scheduler.clear();
    	String sql = "select job_id, job_name, job_group, invoke_target, cron_expression, misfire_policy, "+
 			   		 "	concurrent, status, create_by, create_time, update_by, update_time, remark " +
 			   		 "  from sys_job order by job_id ";
    	List<Job> jobList = db.queryForList(sql, null, Job.class);
		for (Job job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
		}
	}

    /**
     * 获取quartz调度器的计划任务列表（分页查询）
     * @param request
     * @return
     */
    public TableDataInfo selectJobList(HttpServletRequest request, boolean pagination) {
		String job_name = RequestUtil.getValue(request, "job_name");
    	String job_group = RequestUtil.getValue(request, "job_group");
    	String status = RequestUtil.getValue(request, "status");
    	String invoke_target = RequestUtil.getValue(request, "invoke_target");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("select job_id, job_name, job_group, invoke_target, cron_expression, misfire_policy, "+
    			   "	concurrent, status, create_by, create_time, update_by, update_time, remark " +
				   "  from sys_job where 1 = 1 ");

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

		//拼接排序语句
		this.addOrderBySql(request, sql, "create_time desc");
        return this.getRespTableDataInfo(request, sql.toString(), paramList, pagination);
    }

    /**
     * 通过调度任务ID查询调度信息
     * @param job_id 调度任务ID
     * @return 调度任务对象信息
     */
	public Job selectJobById(String job_id) {
    	String sql = "select job_id, job_name, job_group, invoke_target, cron_expression, misfire_policy, "+
		   		 	 "	concurrent, status, create_by, create_time, update_by, update_time, remark " +
		   		 	 "  from sys_job where job_id = ?";
		return db.queryForObject(sql, new Object[]{job_id}, Job.class);
	}

    /**
     * 暂停任务
     * @param job 调度信息
     */
	public int pauseJob(String job_id, String job_group) throws SchedulerException {
		String sql = "update sys_job set status = ? where job_id = ?";
		int result = db.execute(sql, new Object[]{ScheduleConstants.Status.PAUSE.getValue(), job_id});
		if (result > 0) {
			scheduler.pauseJob(ScheduleUtils.getJobKey(Long.parseLong(job_id), job_group));
		}
		return result;
	}

    /**
     * 恢复任务
     * @param job 调度信息
     */
	public int resumeJob(String job_id, String job_group) throws SchedulerException {
		String sql = "update sys_job set status = ? where job_id = ?";
		int result = db.execute(sql, new Object[]{ScheduleConstants.Status.NORMAL.getValue(), job_id});
		if (result > 0) {
			scheduler.resumeJob(ScheduleUtils.getJobKey(Long.parseLong(job_id), job_group));
		}
		return result;
	}

    /**
     * 删除任务后，所对应的trigger也将被删除
     * @param job 调度信息
     */
	public int deleteJob(Job job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();

		String sql = "delete from sys_job where job_id = ?";
		int result = db.execute(sql, new Object[]{jobId});
		if (result > 0) {
			scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return result;
	}

    /**
     * 批量删除调度信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public void deleteJobByIds(String ids) throws SchedulerException {
		String[] jobIds = Convert.toStrArray(ids);
		for (String jobId : jobIds) {
			Job job = selectJobById(jobId);
			deleteJob(job);
		}
	}

    /**
     * 任务调度状态修改
     * @param job 调度信息
     */
	public int changeStatus(HttpServletRequest request) throws SchedulerException {
    	String job_id = RequestUtil.getValue(request, "job_id");
    	String job_group = RequestUtil.getValue(request, "job_group");
    	String status = RequestUtil.getValue(request, "status");
		int rows = 0;
		if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {//恢复任务
			rows = resumeJob(job_id, job_group);
		} else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {//暂停任务
			rows = pauseJob(job_id, job_group);
		}
		return rows;
	}

    /**
     * 立即运行任务
     * @param job 调度信息
     */
	public void run(String job_id) throws SchedulerException {
        Job job = selectJobById(job_id);
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);
        scheduler.triggerJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()), dataMap);
	}

    /**
     * 新增任务
     * @param job 调度信息 调度信息
     */
	public int insertJob(HttpServletRequest request) throws SchedulerException, TaskException {
		String job_name = RequestUtil.getValue(request, "job_name");
    	String job_group = RequestUtil.getValue(request, "job_group");
    	String invoke_target = RequestUtil.getValue(request, "invoke_target");
    	String cron_expression = RequestUtil.getValue(request, "cron_expression");
    	String misfire_policy = RequestUtil.getValue(request, "misfire_policy");
    	String concurrent = RequestUtil.getValue(request, "concurrent");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

		String sql = "insert into sys_job(job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, "+
					 "status, remark, create_by, create_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate()) ";
		long job_id = db.insert(sql, new String[]{job_name, job_group, invoke_target, cron_expression, misfire_policy,
				concurrent, status, remark, operator_id});

		Job job = new Job();
		job.setJobId(job_id);
		job.setJobName(job_name);
		job.setJobGroup(job_group);
		job.setInvokeTarget(invoke_target);
		job.setCronExpression(cron_expression);
		job.setMisfirePolicy(misfire_policy);
		job.setConcurrent(concurrent);
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		job.setRemark(remark);

		ScheduleUtils.createScheduleJob(scheduler, job);
		return 1;
	}

    /**
     * 更新任务的时间表达式
     * @param job 调度信息
     */
	public int updateJob(HttpServletRequest request) throws SchedulerException, TaskException {
		String job_id = RequestUtil.getValue(request, "job_id");
		String job_name = RequestUtil.getValue(request, "job_name");
    	String job_group = RequestUtil.getValue(request, "job_group");
    	String invoke_target = RequestUtil.getValue(request, "invoke_target");
    	String cron_expression = RequestUtil.getValue(request, "cron_expression");
    	String misfire_policy = RequestUtil.getValue(request, "misfire_policy");
    	String concurrent = RequestUtil.getValue(request, "concurrent");
    	String status = RequestUtil.getValue(request, "status");
    	String remark = RequestUtil.getValue(request, "remark");
    	String operator_id = ShiroUtils.getLoginName();

    	Job properties = selectJobById(job_id);
    	String old_job_group = properties.getJobGroup();

		String sql = "update sys_job " +
				 	 "   set job_name = ?, job_group = ?, invoke_target = ?, cron_expression = ?, " +
				 	 "   	 misfire_policy = ?, concurrent = ?, status = ?, " +
				 	 "   	 remark = ?, update_by = ?, update_time = sysdate() " +
				 	 " where job_id = ?";
		int result = db.execute(sql, new Object[]{job_name, job_group, invoke_target, cron_expression, misfire_policy,
				concurrent, status, remark, operator_id, job_id});

		if (result > 0) {
			Job job = new Job();
			job.setJobId(Long.parseLong(job_id));
			job.setJobName(job_name);
			job.setJobGroup(job_group);
			job.setInvokeTarget(invoke_target);
			job.setCronExpression(cron_expression);
			job.setMisfirePolicy(misfire_policy);
			job.setConcurrent(concurrent);
			job.setStatus(status);
			job.setRemark(remark);

			//更新任务
			updateSchedulerJob(job, old_job_group);
		}
		return result;
	}

    /**
     * 更新任务
     * @param job 调度信息
     * @param jobGroup 任务组名
     */
	public void updateSchedulerJob(Job job, String jobGroup) throws SchedulerException, TaskException {
		Long jobId = job.getJobId();
		// 判断是否存在
		JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
		if (scheduler.checkExists(jobKey)) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(jobKey);
		}
		ScheduleUtils.createScheduleJob(scheduler, job);
	}

    /**
     * 校验cron表达式是否有效
     * @param cronExpression 表达式
     * @return 结果
     */
	public boolean checkCronExpressionIsValid(String cronExpression) {
		return CronUtils.isValid(cronExpression);
	}
}
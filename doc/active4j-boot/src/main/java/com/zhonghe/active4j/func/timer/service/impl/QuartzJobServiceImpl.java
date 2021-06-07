package com.zhonghe.active4j.func.timer.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.MyBeanUtils;
import com.zhonghe.active4j.core.util.UUIDUtil;
import com.zhonghe.active4j.func.timer.dao.QuartzJobDao;
import com.zhonghe.active4j.func.timer.entity.QuartzJobEntity;
import com.zhonghe.active4j.func.timer.service.QuartzJobService;
import com.zhonghe.active4j.func.timer.service.QuartzService;
import com.zhonghe.active4j.system.util.SystemUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title QuartzJobServiceImpl.java
 * @description 
		定时任务
 * @time  2019年12月9日 下午4:42:01
 * @author guyp
 * @version 1.0
 */
@Service("quartzJobService")
@Transactional
@Slf4j
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobDao, QuartzJobEntity> implements QuartzJobService {

	@Autowired
	private QuartzService quartzService;

	/**
	 * 
	 * @description
	 *  	保存定时任务
	 * @params
	 *      job 定时任务实体
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月10日 上午10:30:27
	 */
	public AjaxJson saveJob(QuartzJobEntity job) {
		AjaxJson j = new AjaxJson();
		//定义定时任务保存实体
		QuartzJobEntity quartzJob = null;
		try {
			//如果任务id为空，就新增
			if(StringUtils.isEmpty(job.getId())) {
				job.setJobNo(UUIDUtil.getUUID()); //生成唯一任务编号
				job.setJobName(job.getJobGroup() + job.getJobNo()); //任务名为组名+jobNo，应该是唯一
				quartzJob = job;
			}
			//获取原始任务
			else {
				QuartzJobEntity tmp = this.getById(job.getId());
				if(null == tmp) {
					j.setSuccess(false);
					j.setMsg("定时任务保存失败，未获取到相关任务");
					return j;
				}
				//实体赋值
				MyBeanUtils.copyBeanNotNull2Bean(job, tmp);
				quartzJob = tmp;
			}
			//这里执行状态与任务状态保持一致：0：启用/就绪，3：暂停
			quartzJob.setJobExecuteStatus(quartzJob.getJobStatus());
			//保存入库
			this.saveOrUpdate(quartzJob);
			
			//添加定时任务
			boolean blnAdd = quartzService.addJob(quartzJob);
			if(!blnAdd) {
				//更新执行异常状态
				quartzJob.setJobExecuteStatus(QuartzJobEntity.Job_Execute_Status_Error);
				this.saveOrUpdate(quartzJob);
				j.setSuccess(false);
				j.setMsg("定时任务保存失败");
				return j;
			}
		} catch (Exception e) {
			log.error("保存定时任务报错，错误信息：{}", e.getMessage());
			//更新执行异常状态
			quartzJob.setJobExecuteStatus(QuartzJobEntity.Job_Execute_Status_Error);
			this.saveOrUpdate(quartzJob);
			j.setSuccess(false);
			j.setMsg("定时任务保存异常");
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 
	 * @description
	 *  	根据任务id更新状态
	 * @params
	 *      job 定时任务实体
	 * @return void
	 * @author guyp
	 * @time 2019年12月10日 下午10:47:41
	 */
	public void updateJobById(QuartzJobEntity job) {
		this.baseMapper.updateJobById(job);
	}

	/**
	 * 
	 * @description
	 *  	定时任务启用
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午1:33:08
	 */
	public AjaxJson startJob(String id) {
		AjaxJson j = new AjaxJson();
		
		//任务实体获取校验
		QuartzJobEntity job = this.getById(id);
		if(null == job) {
			j.setSuccess(false);
			j.setMsg("任务不存在，请重新选择");
			return j;
		}
		//如果是启用状态，任务不能启用
		if(StringUtils.equals(QuartzJobEntity.Job_Status_Start, job.getJobStatus())) {
			j.setSuccess(false);
			j.setMsg("任务已启用，无需重复启用");
			return j;
		}
		
		//恢复任务
		boolean blnStart = quartzService.resumeJob(job.getJobName(), job.getJobGroup());
		if(!blnStart) {
			j.setSuccess(false);
			j.setMsg("任务启用失败");
			return j;
		}
		//任务状态修改为启用
		job.setJobStatus(QuartzJobEntity.Job_Status_Start);
		//任务执行状态修改为就绪
		job.setJobExecuteStatus(QuartzJobEntity.Job_Execute_Status_Ready);
		//更新实体
		this.saveOrUpdate(job);
		
		return j;
	}

	/**
	 * 
	 * @description
	 *  	定时任务暂停
	 * @params
	 * 		任务id
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月11日 下午1:44:26
	 */
	public AjaxJson pauseJob(String id) {
		AjaxJson j = new AjaxJson();
		
		//任务实体获取校验
		QuartzJobEntity job = this.getById(id);
		if(null == job) {
			j.setSuccess(false);
			j.setMsg("任务不存在，请重新选择");
			return j;
		}
		//如果是暂停状态，任务不能暂停
		if(StringUtils.equals(QuartzJobEntity.Job_Status_Pause, job.getJobStatus())) {
			j.setSuccess(false);
			j.setMsg("任务已暂停，无需重复暂停");
			return j;
		}
		
		//暂停任务
		boolean blnPause = quartzService.pauseJob(job.getJobName(), job.getJobGroup());
		if(!blnPause) {
			j.setSuccess(false);
			j.setMsg("任务暂停失败");
			return j;
		}
		//任务状态修改为暂停
		job.setJobStatus(QuartzJobEntity.Job_Status_Pause);
		//如果任务状态是就绪，就状态修改为暂停，其余显示上次执行状态
		if(StringUtils.equals(QuartzJobEntity.Job_Execute_Status_Ready, job.getJobExecuteStatus())) {
			job.setJobExecuteStatus(QuartzJobEntity.Job_Execute_Status_Pause);
		}
		//更新实体
		this.saveOrUpdate(job);
		
		return j;
	}

	/**
	 * 
	 * @description
	 *  	删除定时任务
	 * @params
	 *      ids 任务ids
	 * @return void
	 * @author guyp
	 * @time 2019年12月11日 下午4:28:17
	 */
	public void delJobs(String ids) {
		//“,”分割成数组
		String[] strIds = ids.split(",");
		if(null != strIds && strIds.length > 0) {
			for(String id : strIds) {
				//任务实体获取校验
				QuartzJobEntity job = this.getById(id);
				if(null != job && !StringUtils.equals(job.getJobExecuteStatus(), QuartzJobEntity.Job_Execute_Status_Running)) {
					//删除定时任务
					boolean blnDel = quartzService.deleteJob(job.getJobName(), job.getJobGroup());
					//当删除成功时，删除库数据
					if(blnDel) {
						this.removeById(id);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @description
	 *  	获取任务明细
	 * @params
	 *      id 任务id
	 * @return Model
	 * @author guyp
	 * @time 2019年12月11日 下午4:53:42
	 */
	public Model getJobDetailModel(String id, Model model) {
		//根据id获取任务实体
		QuartzJobEntity job = this.getById(id);
		if(null != job) {
			//任务分组的处理
			job.setJobGroup(SystemUtils.getDictionaryValue("func_timer_job_group", job.getJobGroup()));
			//执行策略的处理
			job.setMisfirePolicy(SystemUtils.getDictionaryValue("func_timer_job_misfire_policy", job.getMisfirePolicy()));
			//并发执行的处理
			job.setConcurrentStatus(SystemUtils.getDictionaryValue("func_timer_job_concurrent_status", job.getConcurrentStatus()));
			//任务状态的处理
			job.setJobStatus(SystemUtils.getDictionaryValue("func_timer_job_status", job.getJobStatus()));
			//任务执行状态的处理
			job.setJobExecuteStatus(SystemUtils.getDictionaryValue("func_timer_job_execute_status", job.getJobExecuteStatus()));
			//上一次执行时间的处理
			if(null != job.getPreviousTime()) {
				model.addAttribute("previousTime", DateUtils.getDate2Str(job.getPreviousTime()));
			}
			//下一次执行时间的处理
			if(null != job.getNextTime()) {
				model.addAttribute("nextTime", DateUtils.getDate2Str(job.getNextTime()));
			}
			//赋值
			model.addAttribute("job", job);
		}
		return model;
	}

	/**
	 * 
	 * @description
	 *  	立即执行一次任务
	 * @params
	 *      id 任务id
	 * @return boolean
	 * @author guyp
	 * @time 2019年12月12日 上午12:46:26
	 */
	public boolean runAJobNow(String id) {
		QuartzJobEntity job = this.getById(id);
		if(null == job) {
			return false;
		}
		//立即执行一个job
		return quartzService.runAJobNow(job);
	}
	
}

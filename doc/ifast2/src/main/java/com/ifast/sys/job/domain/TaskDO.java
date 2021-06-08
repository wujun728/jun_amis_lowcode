package com.ifast.sys.job.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@TableName("sys_task")
@Data
public class TaskDO extends Model<TaskDO> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    //
    @TableId
    private Long id;
    // cron表达式
    @TableField("cron_expression")
    private String cronExpression;
    // 任务调用的方法名
    @TableField("method_name")
    private String methodName;
    // 任务是否有状态
    @TableField("is_concurrent")
    private String isConcurrent;
    // 任务描述
    private String description;
    // 更新者
    @TableField("update_by")
    private String updateBy;
    // 任务执行时调用哪个类的方法 包名+类名
    @TableField("bean_class")
    private String beanClass;
    // 创建时间
    @TableField("create_date")
    private Date createDate;
    // 任务状态
    @TableField("job_status")
    private String jobStatus;
    // 任务分组
    @TableField("job_group")
    private String jobGroup;
    // 更新时间
    @TableField("update_date")
    private Date updateDate;
    // 创建者
    @TableField("create_by")
    private String createBy;
    // Spring bean
    @TableField("spring_bean")
    private String springBean;
    // 任务名
    @TableField("job_name")
    private String jobName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

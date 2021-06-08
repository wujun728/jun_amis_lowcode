package com.ifast.common.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class BaseDO implements Serializable {
	/** 由mybatis-plus.global-config.sql-injector:com.baomidou.mybatisplus.mapper.LogicSqlInjector自动维护 */
//	@TableLogic
	private Boolean deleted;
	/** 由MyBatisConfig.optimisticLockerInterceptor自动维护 */
	@Version
	private int version;
	/** 由MySQL自动维护 */
	
	@TableField("create_at")
	private Date createAt;
	@TableField("update_at")
	private Date updateAt;
	/** 由LogAspect.logMapper自动维护 */
	@TableField("create_by")
	private Long createBy;
	@TableField("update_by")
	private Long updateBy;
}

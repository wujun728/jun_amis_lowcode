package com.element.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.element.common.utils.PageUtils;
import com.element.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils listSysLogByPage(Map<String, Object> params);

}

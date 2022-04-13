package com.element.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.utils.PageUtils;
import com.element.common.utils.Query;
import com.element.modules.sys.mapper.SysLogMapper;
import com.element.modules.sys.service.SysLogService;
import com.element.modules.sys.entity.SysLogEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils listSysLogByPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<SysLogEntity> page = this.page(
                new Query<SysLogEntity>().getPage(params),
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
        );
        return new PageUtils(page);
    }
}

package com.jqp.admin.rbac.service.impl;

import com.jqp.admin.db.service.JdbcService;
import com.jqp.admin.rbac.data.Config;
import com.jqp.admin.rbac.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Resource
    private JdbcService jdbcService;
    @Override
    public String getValue(String code) {
        Config config = jdbcService.findOne(Config.class, "code", code);
        return config == null ? null : config.getValue();
    }
}

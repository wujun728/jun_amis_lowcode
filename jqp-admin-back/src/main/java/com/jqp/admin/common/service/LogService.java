package com.jqp.admin.common.service;

import com.jqp.admin.common.BaseData;

import java.util.Map;

public interface LogService {
    void log(Map<String, Object> beforeObj, Map<String, Object> afterObj,String tableName);
    void log(BaseData beforeObj, BaseData afterObj);
}

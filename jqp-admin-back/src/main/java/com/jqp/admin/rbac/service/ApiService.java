package com.jqp.admin.rbac.service;

import com.jqp.admin.common.Result;

import java.util.Map;

public interface ApiService {
    Result<String> call(String api, Map<String,Object> context);
    Result call(String method,String api, Map<String,Object> context);
}

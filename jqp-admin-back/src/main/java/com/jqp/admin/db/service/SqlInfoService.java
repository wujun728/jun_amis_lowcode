package com.jqp.admin.db.service;

import com.jqp.admin.common.service.CacheService;
import com.jqp.admin.page.data.SqlInfo;
import com.jqp.admin.page.data.SqlParam;

import java.util.Map;
import java.util.Set;

public interface SqlInfoService extends CacheService<SqlInfo> {
    void resultFields(SqlInfo sqlInfo);
    String getSql(String code);
    String getSql(SqlInfo sqlInfo, Map<String, SqlParam> paramMap, Set<String> sqlCodes);
    String getSql(SqlInfo sqlInfo);
    SqlInfo getSqlInfo(String code);
}

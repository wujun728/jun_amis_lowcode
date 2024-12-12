package com.jqp.admin.db.service;

import com.jqp.admin.common.PageData;
import com.jqp.admin.common.PageParam;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.service.CacheService;
import com.jqp.admin.db.data.ForeignKey;
import com.jqp.admin.db.data.TableInfo;

import java.util.Map;

public interface TableService extends CacheService<Result<TableInfo>> {
    Result<PageData<TableInfo>> queryTable(PageParam pageParam);
    Result<TableInfo> tableInfo(String tableName);
    Result<Void> updateTable(TableInfo tableInfo);

    Result dropTable(String tableName);

    Map<String,String> generateCode(String tableName);
}

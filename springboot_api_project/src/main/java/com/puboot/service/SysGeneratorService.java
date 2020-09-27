package com.puboot.service;

import java.util.List;
import java.util.Map;

public interface SysGeneratorService {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    Boolean generatorCode(String[] tableNames, String moduleName);

}

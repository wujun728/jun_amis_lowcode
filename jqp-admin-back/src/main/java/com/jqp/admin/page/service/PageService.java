package com.jqp.admin.page.service;

import com.jqp.admin.common.CrudData;
import com.jqp.admin.common.PageParam;
import com.jqp.admin.common.Result;
import com.jqp.admin.common.service.CacheService;
import com.jqp.admin.page.data.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

import java.util.Map;

public interface PageService extends CacheService<Page> {
    Result<CrudData<Map<String,Object>>> query(String pageCode,PageParam pageParam);
    Result<CrudData<Map<String,Object>>> queryAll(String pageCode);
    Result<CrudData<Map<String,Object>>> queryAll(String pageCode,Map<String,Object> params);
    Map<String,Object> optionConfig(String pageCode);
    void reload(Page page);
    String getQuerySql(String querySql);
    void save(Page page);
    Page get(Long id);
    Page load(String pageCode);
    void del(Page page);
}

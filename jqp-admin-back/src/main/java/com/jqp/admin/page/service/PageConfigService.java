package com.jqp.admin.page.service;

import com.jqp.admin.page.data.Page;

import java.util.List;
import java.util.Map;

public interface PageConfigService {
    Map<String,Object> getSelectorConfig(String code,String formField);
    List<Map<String,Object>> queryConfigs(Page page);
    List<Map<String,Object>> queryConfigs(Page page,boolean selector);

    Map<String,Object> getCurdJson(String code);
    Map<String,Object> getCurdJson(String code,boolean addTab);
    void setPageConfig(Page page,Map<String,Object> params);
}

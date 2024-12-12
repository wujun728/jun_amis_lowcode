package com.jqp.admin.page.service;

import com.jqp.admin.page.data.Dic;

import java.util.List;
import java.util.Map;

public interface DicService {
    List<Map<String,Object>> options(String code);
    String getLabel(String code,String value);

    Dic get(String code);
    List<String> getLabels(String code);
    Map<String,String> labelValueMap(String code);
    Map<String,String> valueLabelMap(String code);
}

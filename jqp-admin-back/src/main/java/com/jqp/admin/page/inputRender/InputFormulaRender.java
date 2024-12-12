package com.jqp.admin.page.inputRender;

import cn.hutool.extra.spring.SpringUtil;
import com.jqp.admin.page.data.InputField;
import com.jqp.admin.page.service.DicCacheService;
import com.jqp.admin.page.service.DicService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputFormulaRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        if(StringUtils.isNotBlank(field.getFormat())){
            DicCacheService dicCacheService = SpringUtil.getBean(DicCacheService.class);
            List<Map<String, Object>> options = dicCacheService.options(field.getFormat());
            Map<String,Object> fields = new HashMap<>();
            fields.put("label","字段列表");
            fields.put("children",options);

            List<Map<String,Object>> variables = new ArrayList<>();
            variables.add(fields);
            config.put("variables",variables);
        }
    }
}

package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class InputTimeRangeRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("format","HH:mm:ss");
        config.put("timeFormat","HH:mm:ss");
        config.put("inputFormat","HH:mm:ss");
    }
}

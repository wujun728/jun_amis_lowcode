package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class SelectorPopRender extends InputDefaultRender {
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("type","picker");
        config.put("embed",true);
        config.put("cascade",true);
    }
}

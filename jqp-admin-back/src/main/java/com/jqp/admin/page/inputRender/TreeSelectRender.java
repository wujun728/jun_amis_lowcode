package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class TreeSelectRender extends InputTreeRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        super.extra(config,field);
        config.put("type","tree-select");
    }
}

package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class FormulaRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("formula",field.getFormat());
    }
}

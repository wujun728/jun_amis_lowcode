package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class InputProvinceCityCountyRender extends InputDefaultRender {
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("type", "input-city");
        config.put("allowDistrict", true);
        config.put("allowCity", true);
    }
}

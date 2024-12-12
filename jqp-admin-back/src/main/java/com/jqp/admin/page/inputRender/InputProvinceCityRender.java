package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class InputProvinceCityRender extends InputDefaultRender{
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        config.put("type","input-city");
        config.put("allowDistrict",false);
        config.put("allowCity",true);
    }
}

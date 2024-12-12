package com.jqp.admin.page.inputRender;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public class LocationPickerRender extends InputDefaultRender {
    @Override
    protected void extra(Map<String, Object> config, InputField field) {
        //百度地图ak
        config.put("ak","51SEhCogrVzdWmAdolU0FCbEjqKgWRv4");
    }
}

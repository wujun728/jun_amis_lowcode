package com.jqp.admin.common.service;

import com.jqp.admin.common.data.DataListener;

import java.util.Map;

public interface DataListenerTask {
    void call(DataListener dataListener, Map<String, Object> context);
}

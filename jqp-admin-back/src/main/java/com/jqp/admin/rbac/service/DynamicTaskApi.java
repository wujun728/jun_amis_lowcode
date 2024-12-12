package com.jqp.admin.rbac.service;

import com.jqp.admin.rbac.data.DynamicTask;

public interface DynamicTaskApi {
    String execute(DynamicTask task);
}

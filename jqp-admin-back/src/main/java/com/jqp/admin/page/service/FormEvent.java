package com.jqp.admin.page.service;

import com.jqp.admin.common.Result;
import com.jqp.admin.page.data.Form;

import java.util.Map;

public interface FormEvent {
    default Result beforeSave(Map<String,Object> obj, String tableName, Form form){return null;}
    default Result afterSave(Map<String,Object> obj, String tableName, Form form){return null;}
    default void init(Map<String,Object> obj,Form form){};
}

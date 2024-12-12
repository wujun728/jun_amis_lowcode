package com.jqp.admin.page.service;

import com.jqp.admin.page.data.InputField;

import java.util.Map;

public interface InputFieldService {
    Map<String,Object> buildInputField(InputField inputField,boolean selector);
}

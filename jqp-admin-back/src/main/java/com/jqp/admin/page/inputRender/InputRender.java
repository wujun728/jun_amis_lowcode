package com.jqp.admin.page.inputRender;
import com.jqp.admin.page.data.InputField;

import java.util.Map;

public interface InputRender{
    Map<String,Object> render(InputField inputField);
}
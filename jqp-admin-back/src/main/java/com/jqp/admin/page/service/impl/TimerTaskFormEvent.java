package com.jqp.admin.page.service.impl;

import com.jqp.admin.common.Result;
import com.jqp.admin.page.data.Form;
import com.jqp.admin.page.service.FormEvent;
import com.jqp.admin.rbac.service.TimerTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/***
 * 定时任务表单事件
 */
@Service("timerTaskFormEvent")
public class TimerTaskFormEvent implements FormEvent {

    @Resource
    private TimerTaskService timerTaskService;

    @Override
    public Result afterSave(Map<String, Object> obj, String tableName, Form form) {
        //更新定时任务
        Long id = (Long) obj.get("id");
        timerTaskService.updateTask(id);
        return null;
    }
}

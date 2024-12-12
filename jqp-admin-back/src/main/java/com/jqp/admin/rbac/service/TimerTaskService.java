package com.jqp.admin.rbac.service;

import com.jqp.admin.rbac.data.TimerTask;
import com.jqp.admin.rbac.data.TimerTaskRecord;

/**
 * @author hyz
 * @date 2021/4/7 16:41
 */
public interface TimerTaskService {
    void start();
    void stop();
    void start(TimerTask timerTask);
    void stop(TimerTask timerTask);
    void update(TimerTask timerTask);
    void updateSchedulingPattern(TimerTask timerTask);
    void run(TimerTask timerTask, TimerTaskRecord record);

    void updateTask(Long id);
}

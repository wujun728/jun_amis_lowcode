package com.jqp.admin.common.log.service.impl;

import com.jqp.admin.common.log.LoggerMessage;
import com.jqp.admin.common.log.LoggerQueue;
import com.jqp.admin.common.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired(required = false)
    SimpMessagingTemplate messagingTemplate;
    @Override
    public void startLog() {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Runnable runnable= () -> {
            while (true) {
                try {
                    LoggerMessage log = LoggerQueue.poll();
                    if(log!=null){
                        if(messagingTemplate!=null) {
                            messagingTemplate.convertAndSend("/topic/pullLogger",log);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(runnable);

    }
}

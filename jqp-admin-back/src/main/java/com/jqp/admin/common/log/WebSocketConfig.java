package com.jqp.admin.common.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Xcloud-Api By IDEA
 * 配置WebSocket消息代理端点，即stomp服务端
 * 为了连接安全，setAllowedOrigins设置的允许连接的源地址
 * 如果在非这个配置的地址下发起连接会报403
 * 进一步还可以使用addInterceptors设置拦截器，来做相关的鉴权操作
 * Created by LaoWang on 2018/8/25.
 */
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                //.setAllowedOrigins("*")
                .withSockJS();
    }

//    /**
//     * 推送日志到/topic/pullLogger
//     */
//    @PostConstruct
//    public void pushLogger(){
//        System.out.println("????");
//        System.out.println(LoggerQueue.blockingQueue.size());
//        ExecutorService executorService= Executors.newFixedThreadPool(2);
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("开始执行");
//                while (true) {
//                    try {
//                        LoggerMessage log = LoggerQueue.poll();
//                        if(log!=null){
//                            if(messagingTemplate!=null)
//                                System.out.println("发送消息");
//                                messagingTemplate.convertAndSend("/topic/pullLogger",log);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        executorService.submit(runnable);
//    }
}

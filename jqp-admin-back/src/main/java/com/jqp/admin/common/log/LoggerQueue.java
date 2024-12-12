package com.jqp.admin.common.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class LoggerQueue {
    //队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    private static LoggerQueue me = new LoggerQueue();
    //阻塞队列
    public static BlockingQueue blockingQueue = null;
    private synchronized static BlockingQueue getBlockingQueue(){
        if(blockingQueue == null){
            blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);
        }
        return blockingQueue;
    }
    /**
     * 消息入队
     *
     * @param log
     * @return
     */
    public static boolean push(LoggerMessage log) {
        if(blockingQueue == null){
            return false;
        }
        return blockingQueue.add(log);//队列满了就抛出异常，不阻塞
    }


    /**
     * 消息出队
     *
     * @return
     */
    public static LoggerMessage poll() {
        if(blockingQueue == null){
            blockingQueue = getBlockingQueue();
        }
        LoggerMessage result = null;
        try {
            result = (LoggerMessage) blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

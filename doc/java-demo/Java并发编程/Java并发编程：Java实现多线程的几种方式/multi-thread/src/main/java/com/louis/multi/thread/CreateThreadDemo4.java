package com.louis.multi.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadDemo4 {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println("---- 主程序开始运行 ----");
		Date startTime = new Date();
		
		int taskSize = 5;
		// 创建一个线程池,Executors提供了创建各种类型线程池的方法，具体详情请自行查阅
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
		
		// 创建多个有返回值的任务
		List<Future> futureList = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++) {
			Callable callable = new MyCallable(i);
			// 执行任务并获取Future对象
			Future future = executorService.submit(callable);
			futureList.add(future);
		}
		
		// 关闭线程池
		executorService.shutdown();

		// 获取所有并发任务的运行结果
		for (Future future : futureList) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			System.out.println(">>> " + future.get().toString());
		}

		Date endTime = new Date();
		System.out.println("---- 主程序结束运行 ----，程序运行耗时【" + (endTime.getTime() - startTime.getTime()) + "毫秒】");
	}
}

class MyCallable implements Callable<Object> {
	private int taskNum;

	MyCallable(int taskNum) {
		this.taskNum = taskNum;
	}

	public Object call() throws Exception {
		System.out.println(">>> " + taskNum + " 线程任务启动");
		Date startTime = new Date();
		Thread.sleep(1000);
		Date endTime = new Date();
		long time = endTime.getTime() - startTime.getTime();
		System.out.println(">>> " + taskNum + " 线程任务终止");
		return taskNum + "线程任务返回运行结果, 当前任务耗时【" + time + "毫秒】";
	}
}

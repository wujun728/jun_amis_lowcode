package com.louis.multi.thread;

public class CreateThreadDemo2 implements Runnable {

    @Override
    public void run() {
    	System.out.println("当前运行的线程名为： " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
    	CreateThreadDemo2 runnable = new CreateThreadDemo2();
        new Thread(runnable, "MyThread1").start();
        new Thread(runnable, "MyThread2").start();

    }

}

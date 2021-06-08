package com.louis.multi.thread;

public class CreateThreadDemo1 extends Thread {

    public CreateThreadDemo1(String name) {
        // 设置当前线程的名字
        this.setName(name);
    }

    @Override
    public void run() {
    	System.out.println("当前运行的线程名为： " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        // 注意这里，要调用start方法才能启动线程，不能调用run方法
        new CreateThreadDemo1("MyThread1").start();
        new CreateThreadDemo1("MyThread2").start();

    }

}

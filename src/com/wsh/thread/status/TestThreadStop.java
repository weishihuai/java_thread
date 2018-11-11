package com.wsh.thread.status;

/**
 * @Description: 测试停止线程的方法
 * @Author: weishihuai
 * @Date: 2018/11/11 20:57
 * <p>
 * 说明:
 * 1. 线程类中提供一个标识标志该线程是否执行
 * 2. 线程体使用该标识
 * 3. 提供对外的方法改变标识
 * 4. 启动线程之后，根据条件改变线程执行标识
 */
public class TestThreadStop {

    public static void main(String[] args) {
        ThreadFirst threadFirst = new ThreadFirst();
        new Thread(threadFirst).start();

        for (int i = 0; i < 5000; i++) {
            if (i == 2500) {
                threadFirst.stopThread();
            }
            System.out.println("TestThreadStop.main--->" + i);
        }
    }

}

class ThreadFirst implements Runnable {

    private boolean flag;

    ThreadFirst() {
        flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println("ThreadFirst.run.....");
        }
    }

    public void stopThread() {
        this.flag = false;
    }
}

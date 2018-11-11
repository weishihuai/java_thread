package com.wsh.thread.status;

/**
 * @Description: yield()暂停当前线程，执行其他线程
 * @Author: weishihuai
 * @Date: 2018/11/11 21:25
 */
public class TestYieldThread {
    public static void main(String[] args) {
        Stu stu = new Stu();
        Thread thread = new Thread(stu);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if (i == 500) {
                //暂停main()线程，执行其他线程
                //yield()写在哪个线程体就暂停哪个线程
                Thread.yield();
            }
            System.out.println("TestYieldThread.main..." + i);
        }
    }
}

class Stu implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Stu.run...." + i);
        }
    }
}

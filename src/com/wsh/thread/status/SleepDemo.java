package com.wsh.thread.status;

/**
 * @Title: SleepDemo
 * @ProjectName java_thread
 * @Description:
 * @Author WeiShiHuai
 * @Date 2018/11/12 16:45
 */
public class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Process process = new Process();
        Thread thread = new Thread(process);
        thread.setName("线程Process");
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            //阻塞main线程，休眠一秒钟
            Thread.sleep(1000);
        }
    }
}

/**
 * 线程类
 */
class Process implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);

            //休眠一秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

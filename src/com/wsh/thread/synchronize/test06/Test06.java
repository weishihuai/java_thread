package com.wsh.thread.synchronize.test06;

public class Test06 {

    public static void main(String[] args) {
        // 静态代理-真实角色
        AppleRunnable appleRunnable = new AppleRunnable();

        // 静态代理-代理角色
        Thread proxy1 = new Thread(appleRunnable, "zhangsan");
        Thread proxy2 = new Thread(appleRunnable, "lisi");
        Thread proxy3 = new Thread(appleRunnable, "wangwu");

        // 启动线程
        proxy1.start();
        proxy2.start();
        proxy3.start();
    }

}

/**
 * 线程类
 */
class AppleRunnable implements Runnable {

    // 共享资源(苹果总数量)
    private int num = 30;
    //线程运行标识
    private boolean flag = true;


    private void eat() {
        if (num <= 0) {
            flag = false;
            return;
        }

        //锁定范围过小,导致线程不安全
        synchronized (this) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "吃掉第" + (num--) + "个苹果");
        }
    }

    @Override
    public void run() {
        while (flag) {
            eat();
        }
    }

}

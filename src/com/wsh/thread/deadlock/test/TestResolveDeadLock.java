package com.wsh.thread.deadlock.test;

/**
 * @Description: 线程死锁解决方法
 * @Author: weishihuai
 * @Date: 2018/11/25 09:53
 * <p>
 */
public class TestResolveDeadLock {

    public static void main(String[] args) {
        //两个线程抢占同一资源(不可剥夺资源)

        //打印机
        Object printer = new Object();
        //输入设备
        Object inputDevice = new Object();

        FirstThread firstThread = new FirstThread(printer, inputDevice);
        SecondThread secondThread = new SecondThread(printer, inputDevice);

        //启动两个线程，具体看JVM先执行哪一个线程
        Thread first = new Thread(firstThread);
        first.setName("线程1");
        first.start();
        Thread second = new Thread(secondThread);
        second.setName("线程2");
        second.start();
    }


}

/**
 * 线程一
 */
class FirstThread implements Runnable {
    /**
     * 打印机资源
     */
    private Object printer;
    /**
     * 输入设备资源
     */
    private Object inputDevice;

    public FirstThread(Object printer, Object inputDevice) {
        this.printer = printer;
        this.inputDevice = inputDevice;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            new Test().reduce(printer, inputDevice, Thread.currentThread().getName());
        }
    }
}

/**
 * 线程二
 */
class SecondThread implements Runnable {
    /**
     * 打印机资源
     */
    private Object printer;
    /**
     * 输入设备资源
     */
    private Object inputDevice;

    public SecondThread(Object printer, Object inputDevice) {
        this.printer = printer;
        this.inputDevice = inputDevice;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            new Test().reduce(printer, inputDevice, Thread.currentThread().getName());
        }
    }
}

class Test {
    /**
     * 额外的锁、避免两个对象hash值相等的情况(即使很少)
     */
    private static final Object PUBLIC_LOCK = new Object();

    public void reduce(Object printer, Object inputDevice, String name) {
        // 得到锁的hash值
        int printerHash = System.identityHashCode(printer);
        int inputDeviceHash = System.identityHashCode(inputDevice);

        // 根据hash值来上锁
        if (printerHash < inputDeviceHash) {
            synchronized (printer) {
                synchronized (inputDevice) {
                    //逻辑处理
                    System.out.println("Test.reduce-->printer-->inputDevice-->" + name);
                }
            }
        } else if (printerHash > inputDeviceHash) {
            synchronized (inputDevice) {
                synchronized (printer) {
                    //逻辑处理
                    System.out.println("Test.reduce-->inputDevice-->printer-->" + name);
                }
            }
        } else { // 额外的锁、避免两个对象hash值相等的情况
            synchronized (PUBLIC_LOCK) {
                synchronized (printer) {
                    synchronized (inputDevice) {
                        //逻辑处理
                        System.out.println("Test.reduce-->" + name);
                    }
                }
            }
        }

    }

}



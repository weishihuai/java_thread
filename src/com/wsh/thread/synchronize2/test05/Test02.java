package com.wsh.thread.synchronize2.test05;

/**
 * @Description: 懒汉式
 * @Author: weishihuai
 * @Date: 2018/11/15 20:07
 */
public class Test02 {
    public static void main(String[] args) {

        TestThread testThread1 = new TestThread(500);
        TestThread testThread2 = new TestThread(100);

        testThread1.start();
        testThread2.start();
    }

}

class TestThread extends Thread {

    private long time;

    public TestThread(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + JVM.getInstance(time));
    }
}

class JVM {
    /**
     * 1. 构造器私有化,防止外部创建对象
     */
    private JVM() {

    }

    /**
     * 2. 声明一个私有的静态的对象
     */
    private static JVM jvm = null;

    /**
     * 3. 创建一个公共的对外的静态方法访问静态对象,如果没有对象则创建对象
     *
     * @return JVM
     */
    public static JVM getInstance(long time) {
        //存在对象的时候不需要等待,直接返回实例
        if (null == jvm) {
            synchronized (JVM.class) {
                if (null == jvm) {
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jvm = new JVM();
                }
            }
        }
        return jvm;
    }

}
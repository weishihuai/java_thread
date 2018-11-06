package com.wsh.thread.create.method01;

/**
 * @Title: FirstThreadCreatedMethod
 * @ProjectName java_thread
 * @Description: 多线程创建方法一: 继承Thread类,重写run()方法
 * @Author WeiShiHuai
 * @Date 2018/11/6 10:41
 * <p>
 * 说明:
 * 1. 调用start()方法启动线程,不要调用run(),调用run()相当于普通方法调用.
 * 2. main()方法也是一个线程,主线程,main线程
 */
public class FirstThreadCreatedMethod {

    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        SecondThread secondThread = new SecondThread();

        //调用start()方法启动线程
        firstThread.start();
        secondThread.start();

//        调用run()相当于普通方法调用,从上往下依次执行方法
//        firstThread.run();
//        secondThread.run();

        //主线程
        for (int i = 0; i < 5; i++) {
            System.out.println("FirstThreadCreatedMethod.main[Main线程]......" + i);
        }
    }

}

/**
 * 线程一
 */
class FirstThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("FirstThread.run[线程一]......" + i);
        }
    }
}

/**
 * 线程二
 */
class SecondThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("SecondThread.run[线程二]......" + i);
        }
    }
}

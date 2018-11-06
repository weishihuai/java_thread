package com.wsh.thread.create.method02;

/**
 * @Title: SecondThreadCreatedMethod
 * @ProjectName java_thread
 * @Description: 多线程创建方法二: 实现Runnable接口,重写run()方法
 * @Author WeiShiHuai
 * @Date 2018/11/6 11:08
 * <p>
 * 说明:
 * 1. 实现Runnable方式创建线程实际上使用了静态代理模式
 * 2. 通过public Thread(Runnable target) {} 传入Runnable实例对象进行创建线程
 * 3. 调用start()方法启动线程
 */
public class SecondThreadCreatedMethod {

    public static void main(String[] args) {
        //传入实现Runnable接口的线程实例对象
        Thread t1 = new Thread(new ThreadFirst());
        Thread t2 = new Thread(new ThreadSecond());

        //调用start()启动线程
        t1.start();
        t2.start();

        //主线程
        for (int i = 0; i < 5; i++) {
            System.out.println("SecondThreadCreatedMethod.main[Main线程]......" + i);
        }
    }

}

/**
 * 线程一
 */
class ThreadFirst implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("ThreadFirst.run[线程一]......" + i);
        }
    }
}

/**
 * 线程二
 */
class ThreadSecond implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ThreadSecond.run[线程二]......" + i);
        }
    }
}


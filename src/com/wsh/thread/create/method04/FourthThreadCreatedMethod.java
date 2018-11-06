package com.wsh.thread.create.method04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: FourthThreadCreatedMethod
 * @ProjectName java_thread
 * @Description: 多线程创建方法四: 使用线程池创建
 * @Author WeiShiHuai
 * @Date 2018/11/6 13:55
 * <p>
 * 说明:
 * 1. 创建ExecutorService对象,根据具体需求创建合适的线程池.
 * 2. 使用executorService.submit()方法,传入Runnable实例对象.(也可以传入Callable对象,Callable结合submit()这种方式可以获取线程执行完返回的结果)
 * 3. 使用executorService.shutdown()方法销毁线程池.
 */
public class FourthThreadCreatedMethod {

    public static void main(String[] args) {
        //创建线程池

        //newFixedThreadPool: 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待.
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //创建一个可缓存线程池，如果线程池长度超过处理需要，可回收空闲线程，若无可回收，则新建线程.
//        ExecutorService executorService2 = Executors.newCachedThreadPool();

        //创建一个定长线程池，支持定时及周期性任务执行
//        ExecutorService executorService3 = Executors.newScheduledThreadPool(3);

        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
//        ExecutorService executorService4 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            //为线程池分配任务
            executorService.submit(new FirstThread());
            executorService.submit(new SecondThread());
        }

        //main线程
        for (int i = 0; i < 5; i++) {
            System.out.println("FourthThreadCreatedMethod.main[main线程]......" + i);
        }

        //销毁线程池
        executorService.shutdown();
    }
}

/**
 * 线程一
 */
class FirstThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("FirstThread.run[线程一]......" + i);
        }
    }
}

/**
 * 线程二
 */
class SecondThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("SecondThread.run[线程二]......" + i);
        }
    }
}

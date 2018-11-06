package com.wsh.thread.create.method03;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: ThirdThreadCreatedMethod
 * @ProjectName java_thread
 * @Description: 多线程创建方法三: 实现Callable接口,重写call()方法,利用FutureTask创建线程,new Thread(futureTask)
 * @Author WeiShiHuai
 * @Date 2018/11/6 11:36
 * <p>
 * 说明:
 * 1. 实现Callable接口,重写call(),可以指定泛型,可以获取返回结果
 * 2. 创建FutureTask,传入Callable实例对象
 * 3. new Thread(FutureTask task)创建线程
 * 4. 调用thread.start()启动线程
 */
public class ThirdThreadCreatedMethod {

    public static void main(String[] args) {
        //创建FutureTask对象
        FutureTask<String> firstFutureTask = new FutureTask<>(new ThreadFirstCallable());
        FutureTask<Integer> secondFutureTask = new FutureTask<>(new ThreadSecondCallable());

        //创建线程Thread对象,调用start()方法启动线程
        new Thread(firstFutureTask).start();
        new Thread(secondFutureTask).start();

        //获取线程执行后返回的数据
        try {
            String result = firstFutureTask.get();
            System.out.println("result = " + result);
            Integer num = secondFutureTask.get();
            System.out.println("num = " + num);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //主线程
        for (int i = 0; i < 15; i++) {
            System.out.println("ThirdThreadCreatedMethod.main[线程Main]......" + i);
        }
    }

}

class ThreadFirstCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadFirstCallable.call[线程一]......" + i);
        }
        return "ThreadSecondCallable";
    }
}

class ThreadSecondCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadSecondCallable.call[线程二]......" + i);
        }
        return 1;
    }
}

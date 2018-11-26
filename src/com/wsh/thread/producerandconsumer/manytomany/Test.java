package com.wsh.thread.producerandconsumer.manytomany;

/**
 * @Title: Test
 * @ProjectName java_thread
 * @Description: 测试多生产者多消费者场景
 * @Author WeiShiHuai
 * @Date 2018/11/26 17:12
 */
public class Test {
    public static void main(String[] args) {
        //缓冲区,每个线程都公用同一缓冲区
        Buffer buffer = new Buffer();

        //生产者，并绑定缓冲区
        Producer producer1 = new Producer(buffer);
        Producer producer2 = new Producer(buffer);
        Producer producer3 = new Producer(buffer);

        //消费者，并绑定缓冲区
        Consumer consumer1 = new Consumer(buffer);
        Consumer consumer2 = new Consumer(buffer);
        Consumer consumer3 = new Consumer(buffer);

        //启动线程
        new Thread(producer1, "线程一").start();
        new Thread(producer2, "线程二").start();
        new Thread(producer3, "线程三").start();
        new Thread(consumer1, "线程四").start();
        new Thread(consumer2, "线程五").start();
        new Thread(consumer3, "线程六").start();
    }
}

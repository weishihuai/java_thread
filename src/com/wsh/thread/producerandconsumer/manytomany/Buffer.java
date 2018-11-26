package com.wsh.thread.producerandconsumer.manytomany;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Buffer
 * @ProjectName java_thread
 * @Description: 资源缓冲区
 * @Author WeiShiHuai
 * @Date 2018/11/26 16:45
 */
public class Buffer {
    /**
     * 共享资源集合
     */
    private List<Bread> breadList = new ArrayList<>();

    public synchronized void produce(Bread bread, String threadName) {

        //缓冲区已满，说明生产者线程必须停止，唤醒消费者线程进行消费
        while (breadList.size() == 5) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //模拟生产耗费时间
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        breadList.add(bread);

        //唤醒在锁定池中的线程
        this.notifyAll();

        System.out.println(threadName + "【生产】-->" + " name: " + bread.getName() + ", size = " + bread.getSize() + ", 此时缓冲区大小为: " + breadList.size());
    }


    public synchronized void consume(String threadName) {

        //如果缓冲区大小为0，说明没有资源可以共享，此时应该暂停消费者线程，唤醒生产者线程进行生产操作
        while (breadList.size() == 0) {
            try {
                //将消费者线程加入到等待池中
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //模拟消费耗费的时间
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //假设移除第一个元素
        Bread bread = breadList.get(0);
        breadList.remove(bread);

        //消费完成之后唤醒在锁定池中等待资源的线程
        this.notifyAll();

        System.out.println(threadName + "【消费】-->" + " name: " + bread.getName() + ", size = " + bread.getSize() + ", 此时缓冲区大小为: " + breadList.size());
    }

}

package com.wsh.thread.producerandconsumer.onetoone.test03;

/**
 * @Description: 面包类(共享资源)
 * @Author: weishihuai
 * @Date: 2018/11/25 19:53
 */
public class Bread {
    /**
     * 面包名字
     */
    private String name;
    /**
     * 面包大小
     */
    private int size;

    /**
     * 加入标识,用于控制生产者消费者行为
     * flag = true: 表示生产者生产，消费者等待消费，生产完成后通知消费者进行消费。
     * flag = false: 表示消费者消费，生产者等待生产，消费完成后通知生产者生产。
     */
    private boolean flag = true;

    /**
     * 生产方法
     *
     * @param name 名字
     * @param size 大小
     */
    public synchronized void produce(String name, int size) {
        //生产者等待
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //开始生产面包
        this.name = name;
        //模拟网络延时(生产者生产耗费时间)
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.size = size;
        System.out.println("【生产者生产】" + name + "-->" + size);

        //生产完成之后通知消费者进行消费
        this.notify();
        //生产者停止生产
        this.flag = false;
    }

    /**
     * 消费方法
     */
    public synchronized void consume() {
        //此时生产者正在生产，消费者需要等待
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //模拟网络延时(消费者消费耗费时间)
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("【消费者消费】name = " + name + " , size = " + size);

        //消费者消费完成之后通知生产者进行生产
        this.notify();
        //停止消费
        this.flag = true;
    }

}

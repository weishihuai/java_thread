package com.wsh.thread.producerandconsumer.onetoone.test01;

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
     * 生产方法
     *
     * @param name 名字
     * @param size 大小
     */
    public void produce(String name, int size) {
        this.name = name;
        //模拟网络延时(生产者生产耗费时间)
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.size = size;
    }

    /**
     * 消费方法
     */
    public void consume() {
        //模拟网络延时(消费者消费耗费时间)
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + " , size = " + size);
    }

}

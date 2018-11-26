package com.wsh.thread.producerandconsumer.onetoone.test03;

/**
 * @Description: 面包生产者线程
 * @Author: weishihuai
 * @Date: 2018/11/25 19:57
 */
public class BreadProducer implements Runnable {
    /**
     * 面包(共享资源)
     */
    private Bread bread;

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                bread.produce("三明治", 10);
            } else {
                bread.produce("吐司", 20);
            }
        }
    }
}

package com.wsh.thread.producerandconsumer.onetoone.test03;

/**
 * @Description: 面包消费者
 * @Author: weishihuai
 * @Date: 2018/11/25 19:59
 */
public class BreadConsumer implements Runnable {
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
            bread.consume();
        }
    }
}

package com.wsh.thread.producerandconsumer.manytomany;

/**
 * @Title: Consumer
 * @ProjectName java_thread
 * @Description: 消费者线程
 * @Author WeiShiHuai
 * @Date 2018/11/26 17:11
 */
public class Consumer implements Runnable{

    /**
     * 缓冲区资源
     */
    private Buffer buffer = null;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            buffer.consume(Thread.currentThread().getName());
        }
    }
}

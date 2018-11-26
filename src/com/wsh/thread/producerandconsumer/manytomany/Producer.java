package com.wsh.thread.producerandconsumer.manytomany;

/**
 * @Title: Producer
 * @ProjectName java_thread
 * @Description: 生产者线程
 * @Author WeiShiHuai
 * @Date 2018/11/26 17:02
 */
public class Producer implements Runnable {
    public static Integer num = 0;

    /**
     * 缓冲区资源
     */
    private Buffer buffer = null;


    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            //避免多个线程同时操作num,造成出错
            //确保每一次只有一个生产者线程访问该方法
            synchronized (Producer.class) {
                num++;
                Bread bread = new Bread("面包" + num, Math.random() * 10);
                buffer.produce(bread, Thread.currentThread().getName());
            }
        }

    }
}

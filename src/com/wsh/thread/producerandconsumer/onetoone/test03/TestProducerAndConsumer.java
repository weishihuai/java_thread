package com.wsh.thread.producerandconsumer.onetoone.test03;

/**
 * @Description: 测试生产者-消费者模式
 * @Author: weishihuai
 * @Date: 2018/11/25 20:03
 */
public class TestProducerAndConsumer {
    public static void main(String[] args) {
        //1. 创建共享资源
        Bread bread = new Bread();

        //2. 创建生产者、消费者线程
        BreadProducer breadProducer = new BreadProducer();
        breadProducer.setBread(bread);
        BreadConsumer breadConsumer = new BreadConsumer();
        breadConsumer.setBread(bread);
        //3. 启动线程
        new Thread(breadProducer).start();
        new Thread(breadConsumer).start();
    }

}

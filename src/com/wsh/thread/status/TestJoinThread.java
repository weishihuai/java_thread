package com.wsh.thread.status;

/**
 * @Description: join()合并线程
 * @Author: weishihuai
 * @Date: 2018/11/11 21:18
 */
public class TestJoinThread {
    public static void main(String[] args) {

        Study study = new Study();
        Thread thread = new Thread(study);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if (i == 50) {
                try {
                    //暂停main线程，执行调用join()方法的线程体.等join线程执行完再执行main线程
                    //main线程阻塞
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestJoinThread.main...." + i);
        }
    }
}

class Study implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Study.run....join...." + i);
        }
    }
}




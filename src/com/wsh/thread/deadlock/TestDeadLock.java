package com.wsh.thread.deadlock;

/**
 * @Description: 线程死锁示例
 * @Author: weishihuai
 * @Date: 2018/11/22 19:53
 * <p>
 * 说明:
 * 1. 死锁: 多个线程因竞争资源而造成的一种互相等待的现象，若无外力作用，这些进程都将无法向前推进
 * 2. 注意是竞争的不可剥夺资源
 * 3. 生活中的案例: 2个人一起吃饭但是只有一双筷子，2人轮流吃（同时拥有2只筷子才能吃）。某一个时候，一个拿了左筷子，一人拿了右筷子，2个人都同时占用一个资源，等待另一个资源，这个时候甲在等待乙吃完并释放它占有的筷子，同理，乙也在等待甲吃完并释放它占有的筷子，这样就陷入了一个死循环，谁也无法继续吃饭。
 * 4. 死锁产生的必要条件:
 * a. 互斥条件：进程要求对所分配的资源（如打印机）进行排他性控制，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待
 * b. 不剥夺条件：进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。
 * c. 请求和保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
 * d. 循环等待条件：存在一种进程资源的循环等待链，链中每一个进程已获得的资源同时被 链中下一个进程所请求。即存在一个处于等待状态的进程集合{Pl, P2, ..., pn}，其中Pi等 待的资源被P(i+1)占有（i=0, 1, ..., n-1)，Pn等待的资源被P0占有
 * <p>
 * 5. 避免死锁的方法:
 * a. 加锁顺序（线程按照一定的顺序加锁）
 * b. 加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
 * c. 死锁检测(释放锁、重试、设置线程优先级)
 */
public class TestDeadLock {

    public static void main(String[] args) {
        //两个线程抢占同一资源(不可剥夺资源)

        //打印机
        Object printer = new Object();
        //输入设备
        Object inputDevice = new Object();

        FirstThread firstThread = new FirstThread(printer, inputDevice);
        SecondThread secondThread = new SecondThread(printer, inputDevice);
        //启动两个线程，具体看JVM先执行哪一个线程
        new Thread(firstThread).start();
        new Thread(secondThread).start();
    }

}

/**
 * 线程一
 */
class FirstThread implements Runnable {
    /**
     * 打印机资源
     */
    private Object printer;
    /**
     * 输入设备资源
     */
    private Object inputDevice;

    public FirstThread(Object printer, Object inputDevice) {
        this.printer = printer;
        this.inputDevice = inputDevice;
    }

    @Override
    public void run() {
        //假设FirstThread先锁定打印机printer,接着休眠500ms,继续锁定输入设备inputDevice，而此时inputDevice有可能由SecondThread占用着。
        synchronized (printer) {
            System.out.println("FirstThread.run-->" + "锁定printer");
            //模拟网络延时
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //锁定输入设备资源
            synchronized (inputDevice) {
                System.out.println("FirstThread.run-->" + "锁定inputDevice");
            }

        }
    }
}

/**
 * 线程二
 */
class SecondThread implements Runnable {
    /**
     * 打印机资源
     */
    private Object printer;
    /**
     * 输入设备资源
     */
    private Object inputDevice;

    public SecondThread(Object printer, Object inputDevice) {
        this.printer = printer;
        this.inputDevice = inputDevice;
    }

    @Override
    public void run() {
        //假设SecondThread先锁定输入设备inputDevice,接着休眠500ms,继续锁定打印机printer，而此时printer有可能由FirstThread占用着。
        synchronized (inputDevice) {
            System.out.println("SecondThread.run-->" + "锁定inputDevice");
            //模拟网络延时
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //锁定打印机资源
            synchronized (printer) {
                System.out.println("SecondThread.run-->" + "锁定printer");
            }

        }
    }
}

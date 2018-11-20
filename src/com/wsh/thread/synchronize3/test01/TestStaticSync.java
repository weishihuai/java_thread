package com.wsh.thread.synchronize3.test01;

/**
 * @Title: TestStaticSync
 * @ProjectName java_thread
 * @Description: 静态的同步方法
 * @Author WeiShiHuai
 * @Date 2018/11/20 16:46
 * <p>
 * 说明:
 * 1. synchronized、static修饰的方法，由于静态方法不属于对象，而是属于整个类，所以将锁定在static方法所在的类的Class对象(相当于锁定的.class字节码信息).
 * 2. synchronized、static修饰的方法，当线程访问该方法时，它锁的不是synchronized方法所在的对象，而是synchronized方法所在的类所对应的Class对象。
 * 因为不管一个类生成多少个对象，这些对象会对应唯一一个Class对象，因此当线程分别访问同一个类的两个对象的两个static、synchronized方法时，是顺序执行的。
 */
public class TestStaticSync {
    public static void main(String[] args) {
        Person person = new Person();
        Thread firstThread = new FirstThread(person);
        firstThread.setName("线程一");

        // 因为锁定的是类的class字节码，即便传入不同的对象，静态方法同步仍然不允许多个线程同时执行
        // 也可以传入相同的对象，一样是顺序执行
        person = new Person();
        Thread secondThread = new SecondThread(person);
        secondThread.setName("线程二");

        //启动线程
        firstThread.start();
        secondThread.start();
    }

}

class Person {

    /**
     * synchronized static修饰方法相当于synchronized(Person.class),锁定的是整个Person字节码信息
     * 当执行该类某个对象其中一个同步方法的时候,必须等待释放完锁才能执行
     */
    public synchronized static void eat(String name) {
        for (int i = 0; i <= 10; i++) {
            // 模拟网络延时
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【" + name + "】-->" + i);
        }
    }

    /**
     * synchronized static修饰方法相当于synchronized(Person.class),锁定的是整个Person字节码信息
     * 当执行该类某个对象其中一个同步方法的时候,必须等待释放完锁才能执行
     */
    public synchronized static void say(String name) {
        for (int i = 0; i <= 10; i++) {
            // 模拟网络延时
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【" + name + "】-->" + i);
        }
    }
}

/**
 * 第一个线程
 */
class FirstThread extends Thread {
    private Person person;

    public FirstThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        // 访问静态的synchronized方法
        person.eat(Thread.currentThread().getName());
    }

}

/**
 * 第二个线程
 */
class SecondThread extends Thread {
    private Person person;

    public SecondThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        // 访问静态的synchronized方法
        person.say(Thread.currentThread().getName());
    }

}
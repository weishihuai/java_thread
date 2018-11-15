package com.wsh.thread.synchronize2.test06;

/**
 * @Description: 饿汉式
 * @Author: weishihuai
 * @Date: 2018/11/15 20:23
 */
public class Test {
    public static void main(String[] args) {

    }
}

class JVM {

    /**
     * 1.构造器私有化
     */
    private JVM() {

    }

    /**
     * 创建一个对象
     */
    private static JVM jvm = new JVM();

    /**
     * 创建一个公共的方法访问该静态对象
     *
     * @return
     */
    public static JVM getInstance() {
        return jvm;
    }

}

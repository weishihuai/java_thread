package com.wsh.thread.synchronize2.test01;

/**
 * @Description:
 * @Author: weishihuai
 * @Date: 2018/11/15 20:00
 */
public class Test01 {

    public static void main(String[] args) {
        JVM jvm1 = JVM.getInstance();
        JVM jvm2 = JVM.getInstance();
        //true
        System.out.println(jvm1 == jvm2);
    }

}

class JVM {
    /**
     * 1. 构造器私有化,防止外部创建对象
     */
    private JVM() {

    }

    /**
     * 2. 声明一个私有的静态的对象
     */
    private static JVM jvm = null;

    /**
     * 3. 创建一个公共的对外的静态方法访问静态对象,如果没有对象则创建对象
     *
     * @return JVM
     */
    public static JVM getInstance() {
        if (null == jvm) {
            jvm = new JVM();
        }
        return jvm;
    }

}

package com.wsh.thread.producerandconsumer.manytomany;

/**
 * @Title: Bread
 * @ProjectName java_thread
 * @Description: 面包类(共享资源)
 * @Author WeiShiHuai
 * @Date 2018/11/26 16:42
 */
public class Bread {
    private String name;
    private double size;

    public Bread(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

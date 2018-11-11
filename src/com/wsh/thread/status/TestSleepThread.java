package com.wsh.thread.status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 线程休眠Sleep()方法
 * @Author: weishihuai
 * @Date: 2018/11/11 21:31
 * <p>
 * 1. 倒计时
 * 2. 模拟网络延时
 */
public class TestSleepThread {
    public static void main(String[] args) throws InterruptedException {
        Date now = new Date(System.currentTimeMillis() + 1000 * 10);
        long nowTime = now.getTime();

        while (true) {
            System.out.println(new SimpleDateFormat("mm:ss").format(now));
            //下一秒时间
            now = new Date(now.getTime() - 1000);
            //休眠一秒钟
            Thread.sleep(1000);
            //倒计时十秒
            if (nowTime - now.getTime() > 10000) {
                break;
            }
        }

    }
}



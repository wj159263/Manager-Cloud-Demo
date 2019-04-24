package com.wj.desiner._01single;

import java.util.concurrent.CountDownLatch;

/**
 * 开启10条线程去获取单例对象，等每条线程都执行完后，计算时间。
 */
public class SingleTest {
    public static void main(String[] args) throws Exception {

        int threadNum = 10;
        //CountDownLatch能辅助等待10条线程执行完再计数
        final CountDownLatch latch = new CountDownLatch(threadNum);

        long start =  System.currentTimeMillis();
        for(int x = 0;x <threadNum; x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int y = 0; y < 2000;y++) {
                        SingleDemo.getInstance();
                    }
                    //每条线程执行到这就完了，latch.countDown()会让new CountDownLatch(10)里面的 10减1
                    latch.countDown();
                }
            }).start();
        }
        //阻塞，当new CountDownLatch(10)的10一直减1，减到剩下0时就放行。就是等每条线程都执行完后才放行
        latch.await();
        long end =  System.currentTimeMillis();
        System.out.println("总共消耗时间:"+ (end -start));
    }
}

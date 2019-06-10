package com.wj.manager.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LockTest {
    static int x = 0;
    public static void main(String[] args) {
        String zkAddr = "127.0.0.1:2181";
        String lockPath = "/distribute-lock";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(zkAddr)
                .sessionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();
        //curator自带得分布式锁



        for(int i=0;i<100;i++){
            new Thread(()->{
                InterProcessMutex lock = new InterProcessMutex(cf, lockPath);
                try {
                 /*   for (int y=0;y<3;y++){
                        x++;
                        System.out.println(x);
                    }*/

                    lock.acquire();
                    for (int y=0;y<3;y++){
                        x++;
                        System.out.println(x);
                    }
                    lock.release();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }).start();
        }

    }
}

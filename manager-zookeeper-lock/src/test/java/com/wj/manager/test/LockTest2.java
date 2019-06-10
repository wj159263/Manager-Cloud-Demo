package com.wj.manager.test;

import com.wj.manager.lock.ZkLock;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LockTest2 {
    static int x =0;
    public static void main(String[] args) {
        String zkAddr = "127.0.0.1:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(zkAddr)
                .sessionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();


        for(int i=0;i<3;i++){
            new Thread(()->{
                ZkLock zkLock = new ZkLock(cf, "/node_lock", "init");
                try {

                    zkLock.lock();
                    for (int y=0;y<3;y++){
                        x++;
                        System.out.println("x="+x);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("解除unlock");
                    zkLock.unlock();
                }

            },i+"").start();
        }

    }
}

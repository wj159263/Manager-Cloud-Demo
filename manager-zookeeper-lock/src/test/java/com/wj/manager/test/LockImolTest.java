package com.wj.manager.test;

import com.wj.manager.lock.ZkLockImpl;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.locks.Lock;

public class LockImolTest {
    static int x = 0;
    public static void main(String[] args) {
        String zkAddr = "127.0.0.1:2181";
        String lockPath = "/dist_lock";
        ZkClient client = new ZkClient(zkAddr);

        for(int i=0;i<100;i++){
            new Thread(()->{

                Lock lock = new  ZkLockImpl(zkAddr,lockPath,client);
                    try {

                    lock.lock();
                    for (int y=0;y<3;y++){
                        x++;
                        System.out.println(Thread.currentThread().getName()+"=="+x);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                        lock.unlock();
                    }

            }).start();
        }

    }
}

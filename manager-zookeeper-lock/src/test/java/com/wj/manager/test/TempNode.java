package com.wj.manager.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class TempNode {
    static String ip = "127.0.0.1:2181";
    static int seesionTimeout = 5000; //seesion多久不用后超时断开
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(ip)
                .sessionTimeoutMs(seesionTimeout)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();
        try {
            cf.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test1","11".getBytes());
            cf.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test2/t2","22".getBytes());


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cf.close();
        }
    }
}

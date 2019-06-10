package com.wj.manager.test;

import com.wj.manager.lock.ZkLock;

import java.util.concurrent.*;

public class DAaa {
    public static void main(String[] args) throws InterruptedException {
        ZkLock.BlockKit blockKill = new ZkLock.BlockKit();
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("取消阻塞了---");
            blockKill.unBlock();
        }).start();
        System.out.println("开始阻塞了---");
        blockKill.block();
        System.out.println("aaaa");
    }
}

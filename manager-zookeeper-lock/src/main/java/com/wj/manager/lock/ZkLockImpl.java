package com.wj.manager.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZkLockImpl implements Lock{
    Logger logger = LoggerFactory.getLogger(getClass());
    private  String ipStr = "127.0.0.1:2181";
    private  String path = "/dist_lock";
    private ZkClient client = new ZkClient(ipStr);
    private BlockKit blockKit = new BlockKit();

    public ZkLockImpl(String ipStr, String path, ZkClient client) {
        this.ipStr = ipStr;
        this.path = path;
        this.client = client;
        this.client.deleteRecursive(path);
        //path订阅监听器IZkDataListener
        client.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataDeleted(String path1) throws Exception {
                if(path.equals(path1)){
                    System.out.println("解锁...");
                    blockKit.unBlock();
                }
            }

            @Override
            public void handleDataChange(String path, Object data) throws Exception {

            }
        });
    }

    @Override
    public void lock() {
        if(!tryLock()) {
            blockKit.block();
            System.out.println("尝试获取锁");
            lock();
        }
    }

    @Override
    public void unlock() {
        //删除节点
        client.delete(path);
    }

    @Override
    public boolean tryLock() {
       try {
           //不存在节点
           client.createEphemeral(path);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    @Override
    @Deprecated
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    @Deprecated
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    @Deprecated
    public Condition newCondition() {
        return null;
    }


    public static class BlockKit{
         Lock lock = new ReentrantLock();
         Condition condition = lock.newCondition();

        public  void block() {
            try {
                lock.lock();
                condition.await();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }


        }

        public  void unBlock(){
            try {
                lock.lock();
                condition.signal();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }
    }
}

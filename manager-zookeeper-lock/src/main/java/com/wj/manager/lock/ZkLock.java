package com.wj.manager.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZkLock implements Lock {
    private CuratorFramework cf;
    private String lockDir;
    private String lockData;
    private ZkLock.BlockKit blockKit;
    private void aa()throws Exception{

    }
    public ZkLock(CuratorFramework cf, String lockDir, String lockData) {
        this.cf = cf;
        this.lockDir = lockDir;
        this.lockData = lockData;
        blockKit = new ZkLock.BlockKit();
        try {
            this.cf.delete().deletingChildrenIfNeeded().forPath(lockDir);
        } catch (Exception e) {
            System.out.println("Aaaaaaaaa");
        }
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        PathChildrenCache cache = new  PathChildrenCache(cf,lockDir,true);

        cache.getListenable().addListener(new PathChildrenCacheListener() {

            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("监听。。。。。。。。。。。。。");
                String path = event.getData().getPath();
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("新增子节点:" + path);
                        break;
                    case CHILD_UPDATED:
                        System.out.println("子节点数据变化:" + path);
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除子节点==:" + path);
                        if (lockDir.equals(path)){
                            System.out.println("解锁");
                            blockKit.unBlock();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        try {
            //cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        try {
            cf.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(lockDir);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            cf.delete().forPath(lockDir);
            System.out.println("删除"+lockDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static class BlockKit{
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void block()  {
            try {
                lock.lock();
                condition.await();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }


        }

        public void unBlock(){
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

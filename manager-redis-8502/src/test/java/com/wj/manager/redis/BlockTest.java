package com.wj.manager.redis;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockTest {
    public static void main(String[] args) {
        MyCallrable<String> myCallrable = new MyCallrable<>();
        new Thread(myCallrable).start();
        //System.out.println(Thread.currentThread().getName()+"开始了");
        String s = myCallrable.get();
        System.out.println("查看得到的数据..."+s);


    }
}
class MyCallrable<V> implements Runnable{
    V result ;
    private Lock lock = new ReentrantLock();
    private Condition con1 = lock.newCondition();
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"获取数据中...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = (V)UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName()+"获取数据完毕..."+this.result);
        lock.lock();
        con1.signal();
        lock.unlock();
    }

    public V get(){
        if(result != null){
            return result;
        }
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"等待中...");
            //主线程来这说明result为null，为空就通过con1执行await阻塞，然后上面的run方法
            // 执行完后con1.signal()唤醒此处，继续执行，再次返回result即可。
            con1.await();
        } catch (InterruptedException e) {
            System.out.println("eeeee");
            e.printStackTrace();
        }
        lock.unlock();
        return result;
    }
}
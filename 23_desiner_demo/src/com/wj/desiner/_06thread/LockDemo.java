package com.wj.desiner._06thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 按顺序输出aa1次，bb2次，cc3次
 */
public class LockDemo {
   volatile int flag = 1;
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        LockDemo demo = new LockDemo();
        new Thread(()->{
            while (true) {
                try {
                    lock.lock();
                    while (demo.flag != 1) {
                        a.await();
                    }
                    for (int i = 0; i < 1; i++) {
                        System.out.println("aa");
                    }
                    demo.flag = 2;
                    b.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }).start();
        new Thread(()->{
            while (true) {
                try {
                    lock.lock();
                    while (demo.flag != 2) {
                        b.await();
                    }
                    for (int i = 0; i < 2; i++) {
                        System.out.println("bb");
                    }
                    demo.flag = 3;
                    c.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                try {
                    lock.lock();
                    while (demo.flag != 3) {
                        c.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("cc");
                    }
                    demo.flag = 1;
                    a.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();


    }
}

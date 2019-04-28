package com.wj.desiner._06thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueenDemo {
    public static void main(String[] args) {
        MyQueen myQueen = new MyQueen();
        for (int i = 0; i <10 ; i++) {
            final int tem = i;
            new Thread(()->{
                myQueen.put(tem+"");
            },i+"").start();
        }

        for (int i = 0; i <50 ; i++) {
            final int tem = i;
            new Thread(()->{
                myQueen.remove(tem+"");
            },i+"").start();
        }
    }

}
class MyQueen{
    int x;
    Lock lock = new ReentrantLock();
    Condition con1 = lock.newCondition();

    public MyQueen() {
    }
    public MyQueen(int size) {
    }
    public void put(Object e){

        try {
            lock.lock();
            while(this.x >= 5) {
                con1.await();
            }
            this.x++;
            System.out.println(Thread.currentThread().getName() + "放入元素,容器长度为" + this.x);
            con1.signalAll();
        } catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void remove(Object e){

        try {
            lock.lock();
            while(this.x <= 0) {
                con1.await();
            }
            this.x --;
            System.out.println(Thread.currentThread().getName() + "取走元素::::容器长度为" + this.x);
            con1.signalAll();
        } catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
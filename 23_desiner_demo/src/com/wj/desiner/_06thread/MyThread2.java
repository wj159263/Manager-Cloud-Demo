package com.wj.desiner._06thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("fasdadaaaaaaaaaaa");
        return 1;
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        //适配器模式,FutureTask实现了Runnable接口
        FutureTask<Integer> futureTask = new FutureTask<>(myThread2);
        new Thread(futureTask).start();
        try {
            //输出线程FutureTask的返回值。主线程会阻塞直到FutureTask完成，所有该操作应放最后
            Integer result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

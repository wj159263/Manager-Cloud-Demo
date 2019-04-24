package com.wj.desiner._02factory._02abstractFactory;

public interface Engine {
    void run();
    void start();
}
class BadEngine implements  Engine{

    @Override
    public void run() {
        System.out.println("低端引擎，跑的慢");
    }

    @Override
    public void start() {
        System.out.println("低端引擎，启动慢");
    }
}

class GoodEngine implements  Engine{

    @Override
    public void run() {
        System.out.println("高端引擎，跑的快");
    }

    @Override
    public void start() {
        System.out.println("高端引擎，启动快");
    }
}
package com.wj.desiner._02factory._02abstractFactory;

public interface Wheel {
    void skill();
}

class BadWheel implements Wheel{

    @Override
    public void skill() {
        System.out.println("低端轮子，不耐用");
    }
}

class GoodWheel implements Wheel{

    @Override
    public void skill() {
        System.out.println("高端轮子，漂移都没问题");
    }
}
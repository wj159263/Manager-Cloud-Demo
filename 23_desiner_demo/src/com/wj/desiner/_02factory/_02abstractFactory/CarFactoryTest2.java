package com.wj.desiner._02factory._02abstractFactory;

public class CarFactoryTest2 {
    public static void main(String[] args) {
        CarFactory2 factory2 = new GoodCarFactory();
        factory2.createEngine().run();
        CarFactory2 factory3 = new BadCarFactory();
        factory3.createEngine().run();
    }
}

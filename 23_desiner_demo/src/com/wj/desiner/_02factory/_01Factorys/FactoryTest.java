package com.wj.desiner._02factory._01Factorys;

public class FactoryTest {
    public static void main(String[] args) {
        /**
         * 当添加奔驰车时，可以新建个奔驰车工厂，这样不用修改原来的代码。符合开放拓展，关闭修改原则
         */
        CarFactory factory = new AoDiCarFactory();
        factory.createCar().run();
    }
}

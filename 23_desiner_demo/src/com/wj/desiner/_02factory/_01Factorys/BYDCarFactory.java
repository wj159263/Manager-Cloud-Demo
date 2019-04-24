package com.wj.desiner._02factory._01Factorys;

public class BYDCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new BYD();
    }
}

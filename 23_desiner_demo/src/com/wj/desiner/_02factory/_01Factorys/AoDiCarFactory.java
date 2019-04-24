package com.wj.desiner._02factory._01Factorys;

public class AoDiCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new AoDi();
    }
}

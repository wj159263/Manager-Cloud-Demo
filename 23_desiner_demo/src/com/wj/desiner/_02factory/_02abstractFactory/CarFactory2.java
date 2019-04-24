package com.wj.desiner._02factory._02abstractFactory;

public interface CarFactory2 {
    Engine createEngine();
    Seat createSeat();
    Wheel createWheel();
}
class GoodCarFactory implements  CarFactory2{

    @Override
    public Engine createEngine() {
        return new GoodEngine();
    }

    @Override
    public Seat createSeat() {
        return new GoodSeat();
    }

    @Override
    public Wheel createWheel() {
        return new GoodWheel();
    }
}
class BadCarFactory implements  CarFactory2{

    @Override
    public Engine createEngine() {
        return new BadEngine();
    }

    @Override
    public Seat createSeat() {
        return new BadSeat();
    }

    @Override
    public Wheel createWheel() {
        return new BadWheel();
    }
}
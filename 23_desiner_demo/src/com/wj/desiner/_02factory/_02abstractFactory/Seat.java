package com.wj.desiner._02factory._02abstractFactory;

public interface Seat {
    void skill();
}
class BadSeat implements Seat{

    @Override
    public void skill() {
        System.out.println("低端座椅，很硬");
    }
}
class GoodSeat implements Seat{

    @Override
    public void skill() {
        System.out.println("高端座椅，很软");
    }
}
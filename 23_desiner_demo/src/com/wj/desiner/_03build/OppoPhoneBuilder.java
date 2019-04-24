package com.wj.desiner._03build;

public class OppoPhoneBuilder implements PhoneBuilder {
    @Override
    public Brand buildBrand() {
        return new Brand("Oppo");
    }

    @Override
    public Color buildColor() {
        return new Color("蓝色");
    }

    @Override
    public Weight buildWeight() {
        return new Weight("120g");
    }
}

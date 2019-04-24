package com.wj.desiner._03build;

public class ApplePhoneBuilder implements PhoneBuilder {
    @Override
    public Brand buildBrand() {
        return new Brand("苹果");
    }

    @Override
    public Color buildColor() {
        return new Color("白色");
    }

    @Override
    public Weight buildWeight() {
        return new Weight("150g");
    }
}

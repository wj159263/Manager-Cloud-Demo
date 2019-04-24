package com.wj.desiner._03build;

public class ApplePhoneDirector implements PhoneDirector {
    private PhoneBuilder phoneBuilder;

    public ApplePhoneDirector(PhoneBuilder phoneBuilder) {
        this.phoneBuilder = phoneBuilder;
    }

    @Override
    public Phone directPhone() {
        Brand brand = phoneBuilder.buildBrand();
        Color color = phoneBuilder.buildColor();
        Weight weight = phoneBuilder.buildWeight();
        Phone phone = new Phone();
        phone.setBrand(brand);
        phone.setColor(color);
        phone.setWeight(weight);
        return phone;
    }
}

package com.wj.desiner._03build;

public class BuildTest {
    public static void main(String[] args) {
        PhoneDirector phoneDirector = new ApplePhoneDirector(new ApplePhoneBuilder());
        Phone phone = phoneDirector.directPhone();
        System.out.println(phone);
        System.out.println("=======================================");
        phoneDirector = new ApplePhoneDirector(new OppoPhoneBuilder());
        phone = phoneDirector.directPhone();
        System.out.println(phone);

    }
}

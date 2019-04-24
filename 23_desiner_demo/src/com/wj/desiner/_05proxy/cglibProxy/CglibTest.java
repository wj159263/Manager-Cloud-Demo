package com.wj.desiner._05proxy.cglibProxy;

public class CglibTest {
    public static void main(String[] args) {
        PersonCglib<Person> cglib = new PersonCglib<Person>();
        Person instance = cglib.getInstance(new Person());
        Object o = instance.sayHello();
        Object o1 = instance.sayNOno();
        System.out.println("========================");
        System.out.println(o);
        System.out.println(o1);
    }

}

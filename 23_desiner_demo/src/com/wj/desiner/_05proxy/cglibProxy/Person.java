package com.wj.desiner._05proxy.cglibProxy;

public class Person {
    public Object sayHello(){
        System.out.println("person say hello");
        return "返回person";
    }

    public Object sayNOno(){
        System.out.println("person say no no");
        return "返回person no no";
    }

}

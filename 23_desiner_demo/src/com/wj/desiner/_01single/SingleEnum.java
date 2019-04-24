package com.wj.desiner._01single;
//枚举类实现单例。没有懒加载，但是能避免反射破解创建多个对象
public enum SingleEnum {
    //枚举元素本身就是单例的
    INSTANCE;
    private int a = 2;
    public void action(){
        System.out.println(a);
    }
}

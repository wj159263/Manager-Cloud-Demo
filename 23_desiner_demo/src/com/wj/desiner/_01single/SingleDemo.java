package com.wj.desiner._01single;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 效率：
 * 饿汉式 > 静态内部类(本例) > 枚举 > 懒汉式(慢很多)
 */

//可以延时加载，效率也高
public class SingleDemo implements Serializable{
    //SingleData类并没有实际作用，只是静态内部类
    static class SingleData{
        //这里创建的是SingleDemo，不是SingleData
      private final static SingleDemo instance = new SingleDemo();
    }
    private SingleDemo(){}

    //调用该方法才会创建对象
    public static SingleDemo getInstance(){
        return SingleData.instance;
    }
    //反序列化会自动调用本方法,防止反序列化创建多个对象
    private Object readResolve() throws ObjectStreamException{
        return getInstance();
    }
}

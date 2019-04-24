package com.wj.desiner._04adapter;

/**
 * 场景:旧的类(OldThing)使用到新的项目中，但不行改变旧类的代码.新的项目时按照Target的接口规范开发的
 * 1、写个适配器Adapter实现Target从而有target的接口规范，并维护有OldThing的引用，
 * 2、配器Adapter去实现Target的方法中调用OldThing对应的方法
 */
public class TestAdapter {
    public static void main(String[] args) {
       OldThing oldThing =  new OldThingImpl();
        Target adapter = new Adapter(oldThing);
        adapter.start();
    }
}

package com.wj.desiner._04adapter;

/**
 * 旧的类，要被适配
 */
public class OldThingImpl implements OldThing {
    @Override
    public void run() {
        System.out.println("旧的类");
    }
}

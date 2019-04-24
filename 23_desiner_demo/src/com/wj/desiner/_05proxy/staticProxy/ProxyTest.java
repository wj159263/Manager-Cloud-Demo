package com.wj.desiner._05proxy.staticProxy;

import com.wj.desiner._05proxy.Star;
import com.wj.desiner._05proxy.ZhouJieLun;

public class ProxyTest {
    public static void main(String[] args) {
        Star star = new ZhouJieLun();
        ZhouJieLun zhouJieLun = new ZhouJieLun();
        Star proxy = new ProxyOfZhou(star);
        proxy.sing();
    }
}

package com.wj.desiner._05proxy;

import com.wj.desiner._05proxy.Star;

public class ZhouJieLun implements Star {
    @Override
    public void sing() {
        System.out.println("周杰伦唱歌");
    }

    @Override
    public void sign() {
        System.out.println("周杰伦签合同");
    }

    @Override
    public void getMoney() {
        System.out.println("周杰伦收演唱费");
    }
}

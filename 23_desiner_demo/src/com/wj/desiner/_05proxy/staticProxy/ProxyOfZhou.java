package com.wj.desiner._05proxy.staticProxy;

import com.wj.desiner._05proxy.Star;

public class ProxyOfZhou implements Star {
    private Star star;

    public ProxyOfZhou(Star star) {
        this.star = star;
    }

    @Override
    public void sing() {
        System.out.println("proxy帮周杰伦准备演唱的场所");
        star.sing();
        System.out.println("周杰伦演唱过后，proxy帮处理之后的事情");
    }

    @Override
    public void sign() {
        System.out.println("proxy帮周杰伦签合同");
    }

    @Override
    public void getMoney() {
        System.out.println("proxy帮周杰伦收演唱费");
    }
}

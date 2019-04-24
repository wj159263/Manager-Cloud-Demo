package com.wj.desiner._05proxy.dystinProxy;

import com.wj.desiner._05proxy.Star;
import com.wj.desiner._05proxy.ZhouJieLun;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 自定义一个类实现InvocationHandler，里面的invoke方法会把当前调用的方法传入
 * Proxy.newProxyInstance()创建代理对象，并用传入的接口接收
 */
public class DysProxyTest {
    public static void main(String[] args) {
        Star zjl = new ZhouJieLun();
        StarHandler starHandler = new StarHandler(zjl);
        //Proxy.newProxyInstance(zjl.getClass().getClassLoader(), new Class[]{Star.class}, starHandler);
        Star proxyInstance = (Star)Proxy.newProxyInstance(zjl.getClass().getClassLoader(), zjl.getClass().getInterfaces(), starHandler);
        proxyInstance.sing();

    }
}

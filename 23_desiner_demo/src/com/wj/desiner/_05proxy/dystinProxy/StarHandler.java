package com.wj.desiner._05proxy.dystinProxy;

import com.wj.desiner._05proxy.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarHandler implements InvocationHandler {
    Star zjl ;

    public StarHandler(Star zjl) {
        this.zjl = zjl;
    }
    Object instance(Star target){
        this.zjl = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理开始");
        method.invoke(zjl,args);
        System.out.println("jdk动态代理接收");
        //代理的方法有返回值时，可通过下面的返回
        return method.getName();
    }
}

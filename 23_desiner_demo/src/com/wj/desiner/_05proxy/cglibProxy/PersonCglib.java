package com.wj.desiner._05proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonCglib<T> implements MethodInterceptor {

    private T target;//业务类对象，供代理方法中进行真正的业务方法调用

    //相当于JDK动态代理中的绑定
    public T getInstance(T target) {
        this.target = target;  //给业务对象赋值
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return (T)enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(method.getName().equals("sayHello")){
            System.out.println("cglib开始了...........");
            //注意，这里时methodProxy.invokeSuper()，不是method。invoke()
            Object invoke = methodProxy.invokeSuper(o,objects);
            System.out.println("cglib结束了...........");
            return invoke;
        }else {
            return methodProxy.invokeSuper(o,objects);
        }
    }
}

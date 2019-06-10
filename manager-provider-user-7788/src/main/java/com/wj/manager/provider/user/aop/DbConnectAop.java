package com.wj.manager.provider.user.aop;

import com.codingapi.txlcn.tc.aspect.weave.ConnectionCallback;
import com.codingapi.txlcn.tc.aspect.weave.DTXResourceWeaver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
@Aspect
public class DbConnectAop {
    @Autowired
    private DTXResourceWeaver resourceWeaver;
    //@Around("execution(public java.sql.Connection *.getConnection(..))")
    @Around("execution(java.sql.Connection javax.sql.DataSource.getConnection(..))")
    public Connection connectionProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        return (Connection) resourceWeaver.getConnection(new ConnectionCallback(){
            @Override
            public Connection call() throws Throwable {
                return (Connection)joinPoint.proceed();
            }
        });
       /* return (Connection) resourceWeaver.getConnection(()->{
            return (Connection)joinPoint.proceed();
        });*/
    }
}

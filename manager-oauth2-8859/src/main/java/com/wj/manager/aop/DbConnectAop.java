package com.wj.manager.aop;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.codingapi.txlcn.tc.aspect.weave.ConnectionCallback;
import com.codingapi.txlcn.tc.aspect.weave.DTXResourceWeaver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Aspect
public class DbConnectAop {
    @Autowired
    private DTXResourceWeaver resourceWeaver;
    @Around("execution(java.sql.Connection javax.sql.DataSource.getConnection(..))")
    public Connection connectionProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        return (Connection) resourceWeaver.getConnection(new ConnectionCallback(){
            @Override
            public Connection call() throws Throwable {
                Connection c =(Connection)joinPoint.proceed();
                return c;
            }
        });
       /* return (Connection) resourceWeaver.getConnection(()->{
            return (Connection)joinPoint.proceed();
        });*/
    }
}

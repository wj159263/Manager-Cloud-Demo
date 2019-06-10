package com.wj.manager;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("com.wj.manager.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFeignClients
@EnableDistributedTransaction
public class Oauth28859_App {
    public static void main(String[] args) {
        SpringApplication.run(Oauth28859_App.class,args);
    }
}

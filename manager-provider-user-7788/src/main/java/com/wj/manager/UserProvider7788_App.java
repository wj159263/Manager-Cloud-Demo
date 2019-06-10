package com.wj.manager;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@EnableEurekaClient //注册服务
@EnableDiscoveryClient //发现服务
//@EnableFeignClients
@EnableDistributedTransaction
@MapperScan("com.wj.manager.provider.user.mapper")
public class UserProvider7788_App {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider7788_App.class, args);
    }
}
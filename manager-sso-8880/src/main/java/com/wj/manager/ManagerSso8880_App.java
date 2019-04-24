package com.wj.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages ={"com.wj.manager","com.wj.manager.common"} )
@EnableHystrix
@MapperScan("com.wj.manager.sso.mapper")
@ComponentScan(basePackages = {"com.wj.manager","com.wj.manager.common"})
public class ManagerSso8880_App {
    public static void main(String[] args) {
        SpringApplication.run(ManagerSso8880_App.class, args);
    }
}

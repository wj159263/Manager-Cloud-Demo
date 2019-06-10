package com.wj.manager.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
public class RedisApplication6379_App {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication6379_App.class, args);
    }
}

package com.wj.manager.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //开启EurekaServer
public class EurekaServer7000_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7000_App.class, args);
    }

}

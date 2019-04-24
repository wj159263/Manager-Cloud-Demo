package com.wj.manager.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class SpringBootAdmin8085_App {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdmin8085_App.class,args);
    }
}

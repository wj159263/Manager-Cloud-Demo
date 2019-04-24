package com.wj.manager.netconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ManagerConfigCentor8888_App {
    public static void main(String[] args) {
        SpringApplication.run(ManagerConfigCentor8888_App.class,args);
    }
/**
 * 其他所有的项目都加依赖 <artifactId>spring-cloud-starter-config</artifactId>，并配置yml
 * 启动顺序：config8888 -> eureka -> zipkin -> springboot-admin -> provider -> zuul
 *
 */

}

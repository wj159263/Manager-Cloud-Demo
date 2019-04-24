package com.wj.manager.ziplin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@EnableEurekaClient
public class Zipkin3344_App {
    public static void main(String[] args) {
        SpringApplication.run(Zipkin3344_App.class,args);
    }
/**
 * 步骤
 * 1、导包，如pom文件。启动类。yml中auto-time-requests: false
 * 2、在所有其他项目，除了parent外，导包spring-cloud-starter-zipkin，并在其yml添加
 * spring:
 zipkin:
 base-url: http://localhost:3344
 *浏览器输入localhost:3344
 */
}
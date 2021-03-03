package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //EurekaService服务器端启动类，接受其他微服务来注册
public class EurekaService7002_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService7002_App.class,args);
    }
}

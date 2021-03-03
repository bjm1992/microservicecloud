package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(value = "com.atguigu.springcloud.dao")
@SpringBootApplication
@EnableEurekaClient  //本服务会自动注册进Eureka服务列表中
@EnableDiscoveryClient  //服务发现
@EnableCircuitBreaker//对hrstrix熔断机制进行支持
public class DeptProvider8001_hystrix_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_hystrix_App.class,args);
    }
}

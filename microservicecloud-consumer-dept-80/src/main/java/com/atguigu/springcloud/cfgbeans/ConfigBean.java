package com.atguigu.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //客户端负载均衡开启
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //return  new RandomRule();  //随机
        return  new RetryRule();//自定义负载均衡算法
    }

}

package com.example.studentservice.feignclient;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancerConfig {

    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder() {
        return feignBuilder();
    }

}

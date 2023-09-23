package com.example.studentservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    @Bean
    @Scope(value = "singleton")
    public MyBean getBean(){
        return new MyBean();
    }

    @Bean
    @Scope(value = "prototype")
    public SecondBean getNewBean(){
        return new SecondBean();
    }
}

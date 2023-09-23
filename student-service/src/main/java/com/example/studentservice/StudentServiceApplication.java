package com.example.studentservice;

import com.example.studentservice.Config.MyBean;
import com.example.studentservice.Config.SecondBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.example.studentservice.controller", "com.example.studentservice.service"})
@EntityScan("com.example.studentservice.entity")
@EnableJpaRepositories("com.example.studentservice.repository")
@EnableFeignClients("com.example.studentservice.feignclient")
@EnableEurekaClient
public class StudentServiceApplication {

	@Autowired
	private MyBean myBean;

	@Autowired
	private SecondBean secondBean;
	@Value("${address.service.url}")
	private String addressServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder()
				.baseUrl(addressServiceUrl).build();
		return webClient;

	}


	@RestController
	public static class MyController{
		@Autowired
		private MyBean myBean;

		@Autowired
		private SecondBean secondBean;

		@GetMapping("/")
		public String index(){
			return "MyBean is " + myBean.getMessage() + "Second bean is :" + secondBean.getMessage();
		}
	}
}

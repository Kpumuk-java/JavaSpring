package com.kpumuk.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringProductClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringProductClientApplication.class);
	}

}

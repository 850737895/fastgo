package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FastgoSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoSearchServiceApplication.class, args);
	}
}

package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FastgoAdvertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoAdvertServiceApplication.class, args);
	}
}

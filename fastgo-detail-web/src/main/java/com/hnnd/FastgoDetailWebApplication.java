package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FastgoDetailWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoDetailWebApplication.class, args);
	}

}


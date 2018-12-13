package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hnnd.fastgo"})
public class FastgoSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoSearchServiceApplication.class, args);
	}
}

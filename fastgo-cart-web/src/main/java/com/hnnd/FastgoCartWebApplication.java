package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FastgoCartWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoCartWebApplication.class, args);
	}

}


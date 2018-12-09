package com.hnnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by 85073 on 2018/12/9.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hnnd.fastgo")
public class FastgoWebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastgoWebSearchApplication.class, args);
    }

}

package com.hnnd;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hnnd.fastgo"})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@Import(FdfsClientConfig.class)
public class FastgoManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastgoManagerWebApplication.class, args);
	}
}

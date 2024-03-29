package com.mycompany.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableJms
public class ProductSpringElasticApp {

	public static void main(String[] args) {
		SpringApplication.run(ProductSpringElasticApp.class, args);
	}

}

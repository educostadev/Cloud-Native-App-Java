package com.mycompany.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(ProductSpringApp.class, args);
	}

}

package com.mycompany.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductClientSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(ProductClientSpringApp.class, args);
	}

	/**
	 * The @LoadBalanced annotation tells Spring to use the Ribbon load balancer (as Ribbon is in the classpath due to Maven).
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

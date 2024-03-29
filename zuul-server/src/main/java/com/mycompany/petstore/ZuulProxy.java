package com.mycompany.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulProxy{

	public static void main(String[] args){
		SpringApplication.run(ZuulProxy.class,args);
	}

}
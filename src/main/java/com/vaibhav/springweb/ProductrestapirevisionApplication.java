package com.vaibhav.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "PRODUCT API" , version = "1.0.a" , description = "This api is for product"))
public class ProductrestapirevisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductrestapirevisionApplication.class, args);
	}

}

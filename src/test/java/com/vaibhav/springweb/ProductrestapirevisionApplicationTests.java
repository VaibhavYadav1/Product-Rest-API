package com.vaibhav.springweb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.vaibhav.springweb.model.Product1;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductrestapirevisionApplicationTests {
	
	@Value("${productrestapirevision.services.url}")
	private String baseUrl ;
	
	@Test
	void TestgetProduct() {
		
		System.out.println(baseUrl);
		
		RestTemplate restTemplate = new RestTemplate();
		Product1 product = restTemplate.getForObject(baseUrl+"2", Product1.class);
		
		assertNotNull(product);
		assertEquals("nokia" , product.getName());
	}

	@Test
	void TestCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product1 product = new Product1();
		product.setName("Dell");
		product.setDescriptiom("Good");
		product.setPrice(55000);
		Product1 newproduct = restTemplate.postForObject(baseUrl, product, Product1.class);
		assertNotNull(newproduct);
	}
	
	@Test
	void TestUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product1 product = restTemplate.getForObject(baseUrl+"11", Product1.class);
		product.setPrice(43000);
		
		restTemplate.put(baseUrl, product);
		assertNotNull(product);
	}
	
	@Test
	void TestDeleteProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product1 product = restTemplate.getForObject(baseUrl+"14", Product1.class);
		
		restTemplate.delete(baseUrl+"14");
	}
}	
package com.vaibhav.springweb;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.MvcMatchersAuthorizedUrl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.vaibhav.springweb.model.Product1;
import com.vaibhav.springweb.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
class productrestapirevisionMVCTest {
	
	private static final int PRICE = 100;

	private static final String DESCRIPTION = "unit test done sucessfull !!";

	private static final String NAME = "mvc";

	private static final int ID = 1;

	private static final String CONTEXT_URL = "/productapi";

	private static final String PRODUCTAPI_URL = "/productapi/products/";

	@Autowired
	private MockMvc mcv;
	
	@MockBean
	private ProductRepository repo;
	
	@Test
	void testFindAll() throws Exception {
		Product1 product1 = buildProduct();
		
		assertNotNull(product1.getId());
		assertNotNull(product1);
		assertEquals(product1.getName(), NAME);
		
		List<Product1> product = Arrays.asList(product1);
		when(repo.findAll()).thenReturn(product);
		
		mcv.perform(get(PRODUCTAPI_URL).contextPath(CONTEXT_URL));
//		different from bharath
	}
	
    @Test
	void testCreateProduct() throws JsonProcessingException, Exception {
		Product1 product1 = buildProduct();
		when(repo.save(any())).thenReturn(product1);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mcv.perform(post(PRODUCTAPI_URL).contextPath(CONTEXT_URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product1))).andExpect(status().isOk());
		     /* .andExpect(content().json(objectWriter.writeValueAsString(product1)));  */
	}
    
    @Test
	void testUpdateProduct() throws JsonProcessingException, Exception {
		Product1 product1 = buildProduct();
		product1.setPrice(100000);
		when(repo.save(any())).thenReturn(product1);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mcv.perform(put(PRODUCTAPI_URL).contextPath(CONTEXT_URL).contentType(MediaType.APPLICATION_JSON)
				.content(objectWriter.writeValueAsString(product1)));
		     /* .andExpect(content().json(objectWriter.writeValueAsString(product1)));  */
	}
    
    @Test
    void testDeleteProduct() throws Exception {
    	doNothing().when(repo).deleteById(ID);
    	
    	mcv.perform(delete(PRODUCTAPI_URL+ID).contextPath(CONTEXT_URL));
    }
    
//    @Test
//    void testGetProduct() throws Exception {
//    	when(repo.findById(ID));
//    	
//    	mcv.perform(get(PRODUCTAPI_URL+ID).contextPath(CONTEXT_URL));
//    }
    
	private Product1 buildProduct() {
		Product1 product1 = new Product1();
		product1.setId(ID);
		product1.setName(NAME);
		product1.setDescriptiom(DESCRIPTION);
		product1.setPrice(PRICE);
		return product1;
	}
}
package com.vaibhav.springweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.springweb.model.Product1;
import com.vaibhav.springweb.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product rest Endpoint")
//@Hidden            also at method level
public class Controller {

	@Autowired
	ProductRepository respository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	@RequestMapping(value="/products/" ,method=RequestMethod.GET)
	public List<Product1> findAll(){
		return respository.findAll();
	}
	
	@RequestMapping(value="/products/{id}" , method=RequestMethod.GET)
	@Transactional(readOnly = true)
	@Cacheable("Product-cache")		
	@Operation(summary = "Return a Product" , description = "Take ID and return a single product back")

	public @ApiResponse(description = "Product Object") Product1 findProduct(@Parameter(description = "Id for the Product")@PathVariable("id")int id) {
		LOGGER.info("Finding Product by ID : " + id);
		return respository.findById(id).get();
	}
	
	@RequestMapping(value="/products/" , method=RequestMethod.POST)
	public Product1 createProduct(@Valid @RequestBody Product1 product) {
		return respository.save(product);
	}
	
	@RequestMapping(value="/products/" , method=RequestMethod.PUT)
	public Product1 updateProduct(@RequestBody Product1 product) {
		return respository.save(product);
	}	
	
	@RequestMapping(value="/products/{id}" , method=RequestMethod.DELETE)
	@CacheEvict("Product-cache")
	public void deleteProduct(@PathVariable("id") int id) {
		respository.deleteById(id);
	}
	
	@RequestMapping(value="/products/" , method=RequestMethod.PATCH)
	public Product1 updateSingleProduct(@RequestBody Product1 product) {
		return respository.save(product);
	}
}

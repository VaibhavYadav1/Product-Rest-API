package com.vaibhav.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.springweb.model.Product1;

public interface ProductRepository extends JpaRepository<Product1, Integer> {

}

package com.ecom.ecommerce_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecommerce_backend.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByActiveTrue();

	List<Product> findByNameContainingIgnoreCase(String name);

}

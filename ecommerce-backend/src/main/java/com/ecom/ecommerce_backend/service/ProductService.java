package com.ecom.ecommerce_backend.service;

import java.util.List;

import com.ecom.ecommerce_backend.dto.ProductRequestDto;
import com.ecom.ecommerce_backend.dto.ProductResponseDto;

import jakarta.validation.Valid;

public interface ProductService {
	ProductResponseDto addProduct(@Valid ProductRequestDto request);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto getProductById(Long id);

	List<ProductResponseDto> getProductsByCategory(Long categoryId);

	ProductResponseDto updateProduct(Long id, @Valid ProductRequestDto request);

	void deleteProduct(Long id);
}

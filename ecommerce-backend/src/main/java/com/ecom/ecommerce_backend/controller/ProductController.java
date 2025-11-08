package com.ecom.ecommerce_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce_backend.dto.ProductRequestDto;
import com.ecom.ecommerce_backend.dto.ProductResponseDto;
import com.ecom.ecommerce_backend.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 private final ProductService productService;
	    
	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }
	    
	    @PostMapping
	    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto request) {
	        ProductResponseDto response = productService.addProduct(request);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
	        List<ProductResponseDto> products = productService.getAllProducts();
	        return ResponseEntity.ok(products);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
	        ProductResponseDto product = productService.getProductById(id);
	        return ResponseEntity.ok(product);
	    }
	    
	    @GetMapping("/category/{categoryId}")
	    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable Long categoryId) {
	        List<ProductResponseDto> products = productService.getProductsByCategory(categoryId);
	        return ResponseEntity.ok(products);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<ProductResponseDto> updateProduct(
	            @PathVariable Long id,
	            @RequestBody @Valid ProductRequestDto request) {
	        ProductResponseDto updatedProduct = productService.updateProduct(id, request);
	        return ResponseEntity.ok(updatedProduct);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
}

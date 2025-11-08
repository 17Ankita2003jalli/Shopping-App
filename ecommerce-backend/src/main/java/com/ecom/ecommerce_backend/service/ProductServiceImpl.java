package com.ecom.ecommerce_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ecom.ecommerce_backend.dto.ProductRequestDto;
import com.ecom.ecommerce_backend.dto.ProductResponseDto;
import com.ecom.ecommerce_backend.model.Category;
import com.ecom.ecommerce_backend.model.Product;
import com.ecom.ecommerce_backend.repository.CategoryRepo;
import com.ecom.ecommerce_backend.repository.ProductRepo;
import com.ecom.ecommerce_backend.util.ProductMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;

	ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}

	@Override
	@CacheEvict(value = "products", allEntries = true)
	public ProductResponseDto addProduct(@Valid ProductRequestDto request) {
		log.info("⭐⭐⭐ ADD PRODUCT - Database call for: {}", request.getName());
		System.out.println("⭐⭐⭐ ADD PRODUCT - Database call for: " + request.getName());

		Category category = categoryRepo.findById(request.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

		Product product = ProductMapper.mapToEntity(request, category);
		Product savedProduct = productRepo.save(product);

		log.info("✅ Product saved with ID: {}", savedProduct.getId());
		System.out.println("✅ Product saved with ID: " + savedProduct.getId());

		return ProductMapper.mapToDTO(savedProduct);

	}

	@Override
	@Cacheable(value = "products")
	public List<ProductResponseDto> getAllProducts() {
		log.info("🔥🔥🔥 GET ALL PRODUCTS - DATABASE CALL (NOT CACHED) 🔥🔥🔥");
		System.out.println("==========================================");
		System.out.println("🔥 GET ALL PRODUCTS - DATABASE CALL");
		System.out.println("==========================================");

		List<Product> products = productRepo.findAll();

		log.info("📊 Found {} products in database", products.size());
		System.out.println("📊 Found " + products.size() + " products");
		System.out.println("==========================================");

		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "product", key = "#id")
	public ProductResponseDto getProductById(Long id) {
		log.info("🔥 GET PRODUCT BY ID: {} - DATABASE CALL (NOT CACHED)", id);
		System.out.println("==========================================");
		System.out.println("🔥 GET PRODUCT BY ID: " + id + " - DATABASE CALL");
		System.out.println("==========================================");

		Product product = productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		return ProductMapper.mapToDTO(product);
	}

	@Override
	@Cacheable(value = "productsByCategory", key = "#categoryId")
	public List<ProductResponseDto> getProductsByCategory(Long categoryId) {
		log.info("🔥 GET PRODUCTS BY CATEGORY: {} - DATABASE CALL", categoryId);
		System.out.println("🔥 GET PRODUCTS BY CATEGORY: " + categoryId + " - DATABASE CALL");

		List<Product> products = productRepo.findByCategoryId(categoryId);

		System.out.println("📊 Found " + products.size() + " products in category");

		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	@CachePut(value = "product", key = "#id")
	@CacheEvict(value = { "products", "productsByCategory" }, allEntries = true)
	public ProductResponseDto updateProduct(Long id, @Valid ProductRequestDto request) {
		log.info("⭐ UPDATE PRODUCT ID: {} - Database call", id);
		System.out.println("⭐ UPDATE PRODUCT ID: " + id + " - Database call");

		Product existingProduct = productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		Category category = categoryRepo.findById(request.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

		ProductMapper.updateEntity(existingProduct, request, category);
		Product updatedProduct = productRepo.save(existingProduct);

		System.out.println("✅ Product updated successfully");

		return ProductMapper.mapToDTO(updatedProduct);
	}

	@Override
	@CacheEvict(value = { "product", "products", "productsByCategory" }, allEntries = true)
	public void deleteProduct(Long id) {
		log.info("⭐ DELETE PRODUCT ID: {} - Database call", id);
		System.out.println("⭐ DELETE PRODUCT ID: " + id + " - Database call");

		if (!productRepo.existsById(id)) {
			throw new RuntimeException("Product not found with id: " + id);
		}

		productRepo.deleteById(id);
		System.out.println("✅ Product deleted successfully");
	}

}

package com.ecom.ecommerce_backend.util;

import com.ecom.ecommerce_backend.dto.ProductRequestDto;
import com.ecom.ecommerce_backend.dto.ProductResponseDto;
import com.ecom.ecommerce_backend.model.Category;
import com.ecom.ecommerce_backend.model.Product;

public class ProductMapper {
	public static Product mapToEntity(ProductRequestDto dto, Category category) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setImageUrl(dto.getImageUrl());
		product.setPrice(dto.getPrice());
		product.setStockQuantity(dto.getStockQuantity());
		product.setActive(dto.getActive() != null ? dto.getActive() : true);
		product.setCategory(category);
		return product;
	}

	public static ProductResponseDto mapToDTO(Product product) {
		ProductResponseDto dto = new ProductResponseDto();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setImageUrl(product.getImageUrl());
		dto.setPrice(product.getPrice());
		dto.setStockQuantity(product.getStockQuantity());
		dto.setActive(product.getActive());

		if (product.getCategory() != null) {
			dto.setCategoryId(product.getCategory().getId());
			dto.setCategoryName(product.getCategory().getName());
		}

		return dto;
	}

	public static void updateEntity(Product product, ProductRequestDto dto, Category category) {
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setImageUrl(dto.getImageUrl());
		product.setPrice(dto.getPrice());
		product.setStockQuantity(dto.getStockQuantity());
		product.setActive(dto.getActive() != null ? dto.getActive() : true);
		product.setCategory(category);
	}
}

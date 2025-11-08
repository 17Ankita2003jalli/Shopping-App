package com.ecom.ecommerce_backend.util;

//import java.util.Collections;
//import java.util.stream.Collectors;

import com.ecom.ecommerce_backend.dto.CategoryRequestDto;
import com.ecom.ecommerce_backend.dto.CategoryResponseDto;
import com.ecom.ecommerce_backend.model.Category;

public class CategoryMapper {
	
	public static Category mapToEntity(CategoryRequestDto dto) {
	    Category category = new Category();
	    category.setName(dto.getName());
	    return category;
	}

	public static CategoryResponseDto mapToDTO(Category category) {
	    CategoryResponseDto dto = new CategoryResponseDto();
	    dto.setId(category.getId());
	    dto.setName(category.getName());

//	    // FIX: Check if products collection is initialized and not null
//	    if (category.getProducts() != null && !category.getProducts().isEmpty()) {
//	        try {
//	            // Create a safe copy of the collection to avoid ConcurrentModificationException
//	            dto.setProductIds(category.getProducts()
//	                .stream()
//	                .map(product -> product.getId())
//	                .collect(Collectors.toList()));
//	        } catch (Exception e) {
//	            // If there's any issue accessing products, return empty list
//	            dto.setProductIds(Collections.emptyList());
//	        }
//	    } else {
//	        dto.setProductIds(Collections.emptyList());
//	    }

	    return dto;
	}
}

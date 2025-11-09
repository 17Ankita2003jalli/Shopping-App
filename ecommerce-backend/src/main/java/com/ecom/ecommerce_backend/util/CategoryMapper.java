package com.ecom.ecommerce_backend.util;

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

	    return dto;
	}
}

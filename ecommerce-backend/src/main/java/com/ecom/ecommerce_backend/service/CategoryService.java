package com.ecom.ecommerce_backend.service;

import java.util.List;

import com.ecom.ecommerce_backend.dto.CategoryRequestDto;
import com.ecom.ecommerce_backend.dto.CategoryResponseDto;

import jakarta.validation.Valid;

public interface CategoryService {

	CategoryResponseDto addCategory(@Valid CategoryRequestDto request);

	List<CategoryResponseDto> getAllCategory();

}

package com.ecom.ecommerce_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce_backend.dto.CategoryRequestDto;
import com.ecom.ecommerce_backend.dto.CategoryResponseDto;
import com.ecom.ecommerce_backend.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
	private CategoryService categoryService;

//	Dependency injection
	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<CategoryResponseDto> addcategory(@RequestBody @Valid CategoryRequestDto request) {
		CategoryResponseDto response = categoryService.addCategory(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> allCategories() {
		List<CategoryResponseDto> response = categoryService.getAllCategory();
		return ResponseEntity.ok(response);
	}
}

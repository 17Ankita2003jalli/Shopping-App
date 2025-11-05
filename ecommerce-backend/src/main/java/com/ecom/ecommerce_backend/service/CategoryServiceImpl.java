package com.ecom.ecommerce_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ecom.ecommerce_backend.config.CacheConfig;
import com.ecom.ecommerce_backend.dto.CategoryRequestDto;
import com.ecom.ecommerce_backend.dto.CategoryResponseDto;
import com.ecom.ecommerce_backend.model.Category;
import com.ecom.ecommerce_backend.repository.CategoryRepo;
import com.ecom.ecommerce_backend.util.CategoryMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CacheConfig cacheConfig;
	private CategoryRepo categoryRepo;
	
	CategoryServiceImpl(CategoryRepo categoryRepo, CacheConfig cacheConfig){
		this.categoryRepo=categoryRepo;
		this.cacheConfig = cacheConfig;
	}
	@Override
	@CacheEvict(value = "categories", allEntries = true)
	public CategoryResponseDto addCategory(@Valid CategoryRequestDto request) {
		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		log.info("___Adding new category into the database");
//		System.out.println("++++++++++++Adding new category into the database");
		final Category category = CategoryMapper.mapToEntity(request);
		Category savedCatagory=categoryRepo.save(category);
		return CategoryMapper.mapToDTO(savedCatagory);
	    }
	
	@Override
	@Cacheable(value = "categories")
	public List<CategoryResponseDto> getAllCategory() {
		log.info("___fetching the category from the database(Not from cache)");
//		System.out.println("********** new category into the database");
		List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToDTO)
                .collect(Collectors.toList());
	}

}

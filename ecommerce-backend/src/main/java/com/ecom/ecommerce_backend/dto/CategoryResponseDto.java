package com.ecom.ecommerce_backend.dto;

import java.util.List;

import lombok.Data;
@Data
public class CategoryResponseDto {
	private Long id;
    private String name;
    private List<Long> productIds;
}

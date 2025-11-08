package com.ecom.ecommerce_backend.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductResponseDto {
	private Long id;
	private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean active;
    private Long categoryId;
    private String categoryName;
}

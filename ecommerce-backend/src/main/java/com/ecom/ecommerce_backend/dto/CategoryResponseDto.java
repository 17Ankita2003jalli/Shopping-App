package com.ecom.ecommerce_backend.dto;

import lombok.Data;

@Data
public class CategoryResponseDto {
	private Long id;
	private String name;
//    I am not showing any product details along with the category GET method so i am commenting the product list.
//    private List<Long> productIds;
}

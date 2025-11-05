package com.ecom.ecommerce_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecommerce_backend.model.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}

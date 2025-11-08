package com.ecom.ecommerce_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.ecommerce_backend.model.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
	@Query("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :id")
	Optional<Category> findByIdWithProducts(@Param("id") Long id);

	@Query("SELECT c FROM Category c LEFT JOIN FETCH c.products")
	List<Category> findAllWithProducts();

}

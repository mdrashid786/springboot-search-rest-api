package com.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaguides.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("from Product As p where p.name LIKE CONCAT('%',:query,'%')"
			+ " Or p.description LIKE CONCAT('%',:query,'%')")
	public List<Product> searchProduct(String query);

	@Query("from Product As p where p.id=:id")
	public List<Product> findProductById(Long id);
}

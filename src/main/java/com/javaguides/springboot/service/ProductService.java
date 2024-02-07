package com.javaguides.springboot.service;

import java.util.List;

import com.javaguides.springboot.entity.Product;

public interface ProductService {
	
	public List<Product> searchProduct(String query);
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product, Long id);
	
	public Product deleteProduct(Long id);

	public List<Product> getAllProduct();

	public Product getProductFromId(Long id);
	

}

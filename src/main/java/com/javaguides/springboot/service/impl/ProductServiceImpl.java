package com.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.springboot.entity.Product;
import com.javaguides.springboot.exception.ProductNotFoundException;
import com.javaguides.springboot.repository.ProductRepository;
import com.javaguides.springboot.service.ProductService;

import javassist.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public List<Product> searchProduct(String query) {
		return productRepository.searchProduct(query);
	}


	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}


	@Override
	public Product updateProduct(Product product,Long id) {
		Optional<Product> existsProduct=productRepository.findById(id);
		if (existsProduct.isPresent()) {
			Product updateNew= existsProduct.get();
			updateNew.setSku(product.getSku());
			updateNew.setName(product.getName());
			updateNew.setActive(product.isActive());
			updateNew.setDescription(product.getDescription());
			updateNew.setImageUrl(product.getImageUrl());
			return productRepository.save(updateNew);
		}
		
		 throw new ProductNotFoundException("Product not found with id: " + id);
	}


	@Override
	public Product deleteProduct(Long id) {
	    Optional<Product> delete = productRepository.findById(id);
	    if (delete.isPresent()) {
	        Product dlt = delete.get();
	        productRepository.delete(dlt);
	        return dlt;
	    }
	    throw new ProductNotFoundException("Product not found with id: " + id);
	}


	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}


	@Override
	public Product getProductFromId(Long id) {
		Optional<Product> existsProduct=productRepository.findById(id);
		if (existsProduct.isPresent()) {
			Product product=existsProduct.get();
			return product;
		}
		else {
			 throw new ProductNotFoundException("Product not found with id: " + id);
		}
		
		
	}

	

}

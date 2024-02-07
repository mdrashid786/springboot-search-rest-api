package com.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.springboot.entity.Product;
import com.javaguides.springboot.exception.ProductNotFoundException;
import com.javaguides.springboot.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("all")
	public ResponseEntity<List<Product>> findAllProduct(){
		List<Product> productList=productService.getAllProduct();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<Product> getProductFromId(@PathVariable Long id) {
		Product products=productService.getProductFromId(id);
		if (products!=null) {
			return new ResponseEntity<Product>(products,HttpStatus.OK);
		}
		else {
			  throw new ProductNotFoundException("Product not found with id: " + id);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> getProduct(@RequestParam String query) {
		List<Product> products=productService.searchProduct(query);
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		
		Product add=productService.addProduct(product);
		if (add!=null) {
			String message="Product Added successfully";
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else {
			String message="Somethings is wrong";
			return new ResponseEntity<String>(message,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
		Product update =productService.updateProduct(product,id);
		if (update!=null) {
			String message="Product Updated successfully";
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else {
			String message="Somethings is wrong";
			return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
	    try {
	        Product delete = productService.deleteProduct(id);
	        if (delete != null) {
	            String message = "Product Deleted successfully";
	            return ResponseEntity.ok(message);
	        } else {
	            String message = "Product not found";
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	        }
	    } catch (ProductNotFoundException e) {
	        String message = "Product not found";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	    }
	}

	
	

}

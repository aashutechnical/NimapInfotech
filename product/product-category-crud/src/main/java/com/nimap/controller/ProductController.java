package com.nimap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import com.nimap.dto.ResponseStructure;
import com.nimap.entity.Product;
import com.nimap.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> getCategoryById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(@RequestParam int page) {
		return productService.getAllProducts(PageRequest.of(page - 1, 5));
	}

	@PutMapping("/{di}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int di , @RequestBody Product product) {
		return productService.updateProduct(di, product);
	}
	
	@DeleteMapping("/{di}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int di) {
		return productService.deleteProduct(di);
	}

}

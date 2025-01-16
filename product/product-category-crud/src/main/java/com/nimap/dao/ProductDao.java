package com.nimap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nimap.entity.Product;
import com.nimap.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		productRepository.save(product);
		return product;
	}

	public Optional<Product> getProductById(int id) {
		return productRepository.findById(id);
	}

	public List<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).getContent();
	}
	
	public Product deleteProduct(Product product) {
		productRepository.delete(product);
		return product;
	}

}

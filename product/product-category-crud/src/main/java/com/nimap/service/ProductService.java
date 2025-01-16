package com.nimap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nimap.dao.CategoryDao;
import com.nimap.dao.ProductDao;
import com.nimap.dto.ResponseStructure;
import com.nimap.entity.Category;
import com.nimap.entity.Product;
import com.nimap.exception.DoesNotExistException;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product) {
		int categoryId = product.getCategoryId();
		Optional<Category> optional = categoryDao.getCategoryById(categoryId);
		if (optional.isPresent()) {
			product.setCategory(optional.get());
			Product prod = productDao.addProduct(product);
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>(HttpStatus.CREATED.value(),"success", prod);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
		}
		throw new DoesNotExistException("Category With The ID : " + categoryId + " : Does Not Exist");

	}
	
	public ResponseEntity<ResponseStructure<Product>> getProductById(int id){
		Optional<Product> optional = productDao.getProductById(id);
		if(optional.isPresent()) {
			Product product= optional.get();
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>(HttpStatus.OK.value(), "success", product );
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
			
		}
		
			throw new DoesNotExistException("Product With The ID : " + id + " : Does Not Exist");
		
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(Pageable pageable) {
		List<Product> list = productDao.getAllProducts(pageable);
		if (!(list.isEmpty())) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>(HttpStatus.OK.value(),"success", list);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.OK);
		}
		throw new DoesNotExistException("Products Does Not Exists");

	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id ,  Product product){
		Optional<Product> optional = productDao.getProductById(id);
		if(optional.isPresent()) 
		{
			int catId = product.getCategoryId();
			Optional<Category> optional2 = categoryDao.getCategoryById(catId);
			if(optional2.isPresent()) 
			{
			product.setCategory(optional2.get());
			product.setId(id);
			Product prod = productDao.addProduct(product);
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>(HttpStatus.OK.value(),"successfully updated updated the resources", prod);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
			}
			throw new DoesNotExistException("Category With The ID : " + catId + " : Does Not Exist");
			
		}
		throw new DoesNotExistException("Product With The ID : " + id + " : Does Not Exist");
	}
	
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id){
		Optional<Product> optional = productDao.getProductById(id);
		if(optional.isPresent()) {
			Product product = productDao.deleteProduct(optional.get());
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>(HttpStatus.OK.value(),"successfully deleted the resources", product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		}
		throw new DoesNotExistException("Product With The ID : " + id + " : Does Not Exist");
	} 


}

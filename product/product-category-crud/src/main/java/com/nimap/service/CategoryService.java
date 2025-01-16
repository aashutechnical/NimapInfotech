package com.nimap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nimap.dao.CategoryDao;
import com.nimap.dto.ResponseStructure;
import com.nimap.entity.Category;
import com.nimap.exception.DoesNotExistException;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Category>> addCategory(Category category) {
		Category cat = categoryDao.addCategory(category);
		ResponseStructure<Category> responseStructure = new ResponseStructure<Category>(HttpStatus.CREATED.value(),
				"success", cat);
		return new ResponseEntity<ResponseStructure<Category>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Category>> getCategoryById(int id) {
		Optional<Category> optional = categoryDao.getCategoryById(id);
		if (optional.isPresent()) {
			Category category = optional.get();
			ResponseStructure<Category> responseStructure = new ResponseStructure<Category>(HttpStatus.OK.value(),
					"success", category);
			return new ResponseEntity<ResponseStructure<Category>>(responseStructure, HttpStatus.OK);

		}

		throw new DoesNotExistException("Category With The ID : " + id + " : Does Not Exist");

	}

	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategories(Pageable pageable) {
		List<Category> list = categoryDao.getAllCategories(pageable);
		if (!(list.isEmpty())) {
			ResponseStructure<List<Category>> responseStructure = new ResponseStructure<List<Category>>(HttpStatus.OK.value(),"success", list);
			return new ResponseEntity<ResponseStructure<List<Category>>>(responseStructure, HttpStatus.OK);
		}
		throw new DoesNotExistException("Category Does Not Exists");

	}
	
	public ResponseEntity<ResponseStructure<Category>> updateCategory(int id , Category category){
		Optional<Category> optional = categoryDao.getCategoryById(id);
		if(optional.isPresent()) {
			category.setId(id);
			Category cat = categoryDao.addCategory(category);
			ResponseStructure<Category> responseStructure = new ResponseStructure<Category>(HttpStatus.OK.value(),"successfully updated the resources", cat);
			return new ResponseEntity<ResponseStructure<Category>>(responseStructure, HttpStatus.OK);
		}
		throw new DoesNotExistException("Category With The ID : " + id + " : Does Not Exist");
	}
	
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(int id){
		Optional<Category> optional = categoryDao.getCategoryById(id);
		if(optional.isPresent()) {
			Category category = categoryDao.deleteCategory(optional.get());
			ResponseStructure<Category> responseStructure = new ResponseStructure<Category>(HttpStatus.OK.value(),"successfully deleted the resources", category);
			return new ResponseEntity<ResponseStructure<Category>>(responseStructure, HttpStatus.OK);
		}
		throw new DoesNotExistException("Category With The ID : " + id + " : Does Not Exist");
	} 

}

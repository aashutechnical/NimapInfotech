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
import com.nimap.entity.Category;
import com.nimap.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/{di}")
	public ResponseEntity<ResponseStructure<Category>> getCategoryById(@PathVariable int di) {
		return categoryService.getCategoryById(di);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategories(@RequestParam int page) {
		return categoryService.getAllCategories(PageRequest.of(page-1, 2));
	}
	
	@PutMapping("/{di}")
	public ResponseEntity<ResponseStructure<Category>> updateCategory(@PathVariable int di , @RequestBody Category category ) {
		return categoryService.updateCategory(di, category);
	}
	
	@DeleteMapping("/{di}")
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(@PathVariable int di) {
		return categoryService.deleteCategory(di);
	}

}

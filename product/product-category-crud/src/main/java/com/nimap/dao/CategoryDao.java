package com.nimap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nimap.entity.Category;
import com.nimap.repository.CategoryRepository;

@Repository
public class CategoryDao {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category addCategory(Category category) {
		categoryRepository.save(category);
		return category;
	}

	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}

	public List<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable).getContent();
	}
	
	public Category deleteCategory(Category category) {
		categoryRepository.delete(category);
		return category;
	}

}

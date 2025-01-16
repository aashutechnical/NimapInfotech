package com.nimap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	@Transient
	private int categoryId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn
	@JsonIgnoreProperties("products")
	private Category category;

	@JsonProperty("categoryId")
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	@JsonIgnore
	public int getCategoryId() {
		return categoryId;
	}

}

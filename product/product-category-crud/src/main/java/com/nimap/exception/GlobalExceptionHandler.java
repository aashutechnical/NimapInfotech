package com.nimap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.nimap.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler {
	
public ResponseEntity<ResponseStructure<String>> handleCategoryDoesNotPresentException(DoesNotExistException exception){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>(HttpStatus.NOT_FOUND.value(), "not found", exception.getMessage());
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
		return responseEntity;
	}

}

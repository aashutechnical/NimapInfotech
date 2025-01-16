package com.nimap.exception;

@SuppressWarnings("serial")
public class DoesNotExistException extends RuntimeException {

	private String message;

	public DoesNotExistException() {
		// TODO Auto-generated constructor stub
	}

	public DoesNotExistException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}

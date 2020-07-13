package com.rest.react.webservice.todo.todos;

import org.springframework.http.HttpStatus;

public class todoException extends RuntimeException {

	private HttpStatus status;

	private static final long serialVersionUID = 1L;

	public todoException(String message) {
		super(message);
	}

	public todoException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public todoException(String message, Throwable cause, HttpStatus code) {
		super(message, cause);
		this.status = code;
	}
	
	public todoException(String message, HttpStatus code) {
		super(message);
		this.status = code;
	}
	
	public HttpStatus getCode() {
		return status;
	}

}

package com.rest.react.webservice.todo.jwt.resource;

public class AuthenticationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);

		System.out.println(this.getClass().getName() +" at " + Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
}

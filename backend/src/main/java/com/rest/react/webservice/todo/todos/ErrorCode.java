package com.rest.react.webservice.todo.todos;

public enum ErrorCode {
	
	SOME_ERROR(500);
	
	private int code; 

	ErrorCode(int code) {
		this.code = code;
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}
	
	
}

package com.demo.springboot.exception;

public class TodoCreationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public String message;
	
	public TodoCreationException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.demo.springboot.exception;

public class TodoLoginException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public TodoLoginException(String message){
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

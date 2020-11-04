package com.onlineschool.demo.rest;

public class InstructorNotFoundException extends RuntimeException {

	public InstructorNotFoundException() {
	}

	public InstructorNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InstructorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public InstructorNotFoundException(String message) {
		super(message);
	}

	public InstructorNotFoundException(Throwable cause) {
		super(cause);
	}
	
}

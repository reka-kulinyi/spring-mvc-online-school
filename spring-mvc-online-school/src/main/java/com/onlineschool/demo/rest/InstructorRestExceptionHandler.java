package com.onlineschool.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InstructorRestExceptionHandler {
	// exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<InstructorErrorResponse> handleException(InstructorNotFoundException ex) {
		
		InstructorErrorResponse error = new InstructorErrorResponse(HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(), 
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// exception handler to catch any exception
	@ExceptionHandler
	public ResponseEntity<InstructorErrorResponse> handleException(Exception ex) {
		
		InstructorErrorResponse error = new InstructorErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(), 
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

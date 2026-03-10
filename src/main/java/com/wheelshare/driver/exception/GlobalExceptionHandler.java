package com.wheelshare.driver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDriverNotFound(DriverNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404,ex.getMessage(),request.getRequestURI()));
	}
	
	@ExceptionHandler(DriverNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleDriverNotAvailable(DriverNotAvailableException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409,ex.getMessage(),request.getRequestURI()));
	}
	
	@ExceptionHandler(DriverAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleDriverAlreadyExists(DriverAlreadyExistsException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409, ex.getMessage(),request.getRequestURI()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex,HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, "Internal server err, plz try again later",request.getRequestURI()));
	}

}

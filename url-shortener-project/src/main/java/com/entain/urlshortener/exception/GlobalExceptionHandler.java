package com.entain.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(UrlNotFoundException.class)
	    public ResponseEntity<String> handleNotFound(UrlNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleInvalid(MethodArgumentNotValidException ex) {
	        return ResponseEntity.badRequest().body("Invalid URL");
	    }
	}

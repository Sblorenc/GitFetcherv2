package com.example.GitFetcherV2.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.GitFetcherV2.UserNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiErrorResponse>handleApiException(
			UserNotFoundException ex) {
		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),"User not found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
}

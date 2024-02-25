package com.example.GitFetcherV2.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
	private int status;
	private String message;
	
	
	public ApiErrorResponse(int status, String message) {
		super();
		this.status=status;
		this.message=message;
	}
	
}

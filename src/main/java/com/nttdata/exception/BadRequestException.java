package com.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 4932697646085252389L;

	public BadRequestException() {
		super();
	}
	
	public BadRequestException(Exception e) {
		super(e);
	}
	
	public BadRequestException(String message) {
		super(message);
	}
}

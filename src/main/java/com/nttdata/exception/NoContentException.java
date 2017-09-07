package com.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{

	private static final long serialVersionUID = 4932697646085252389L;

	public NoContentException() {
		super();
	}
	
	public NoContentException(Exception e) {
		super(e);
	}
	
	public NoContentException(String message) {
		super(message);
	}
}

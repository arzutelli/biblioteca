package com.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // gestisce l'associazione tra l'eccezione e l'errore http 404
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4932697646085252389L;

	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(Exception e) {
		super(e);
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}

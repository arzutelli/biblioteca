package com.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT) // gestisce l'associazione tra l'eccezione e l'errore http 409
public class ResourceConflictException extends RuntimeException{

	private static final long serialVersionUID = 4932697646085252389L;

	public ResourceConflictException() {
		super();
	}
	
	public ResourceConflictException(Exception e) {
		super(e);
	}
	
	public ResourceConflictException(String message) {
		super(message);
	}
}

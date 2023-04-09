package com.dev.hroauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AttributeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AttributeException(String message) {
		super(message);
	}
}

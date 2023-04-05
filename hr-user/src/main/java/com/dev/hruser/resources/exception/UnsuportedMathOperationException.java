package com.dev.hruser.resources.exception;

public class UnsuportedMathOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnsuportedMathOperationException(String exception) {
		super(exception);
	}

}

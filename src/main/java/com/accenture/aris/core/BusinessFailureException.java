package com.accenture.aris.core;

public class BusinessFailureException extends RuntimeException {

	private static final long serialVersionUID = -3179384074875691695L;

	public BusinessFailureException() {
	
	}

	public BusinessFailureException(String message) {
		super(message);
	}

	public BusinessFailureException(Throwable cause) {
		super(cause);
	}

	public BusinessFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}

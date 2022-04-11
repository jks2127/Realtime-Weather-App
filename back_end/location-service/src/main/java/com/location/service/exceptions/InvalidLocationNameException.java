package com.location.service.exceptions;

public class InvalidLocationNameException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public InvalidLocationNameException(String message) {
		super(message);
	}
}

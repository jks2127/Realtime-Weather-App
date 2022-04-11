package com.location.service.exceptions;

public class LocationAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public LocationAlreadyExistsException(String message) {
		super(message);
	}
}

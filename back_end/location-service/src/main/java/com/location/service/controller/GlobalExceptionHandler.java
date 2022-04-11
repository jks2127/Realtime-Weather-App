package com.location.service.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.location.service.exceptions.InvalidLocationNameException;
import com.location.service.exceptions.LocationAlreadyExistsException;
import com.location.service.exceptions.LocationNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * Method is used to handle InvalidLocationNameException.
     * 
     * @param e : InvalidLocationNameException class object.
     * @return ResponseEntity : String containing the error message.
     */
    @ExceptionHandler({ InvalidLocationNameException.class })
    public ResponseEntity<String> invalidLocationNameException(InvalidLocationNameException e) {
    	
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
    
	/**
     * Method is used to handle LocationAlreadyExistsException.
     * 
     * @param e : LocationAlreadyExistsException class object.
     * @return ResponseEntity : String containing the error message.
     */
    @ExceptionHandler({ LocationAlreadyExistsException.class })
    public ResponseEntity<String> locationAlreadyExistsException(LocationAlreadyExistsException e) {
    	
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
    
	/**
     * Method is used to handle LocationNotFoundException.
     * 
     * @param e : LocationNotFoundException class object.
     * @return ResponseEntity : String containing the error message.
     */
    @ExceptionHandler({ LocationNotFoundException.class })
    public ResponseEntity<String> locationNotFoundException(LocationNotFoundException e) {
    	
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    } 
}

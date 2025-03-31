package com.bisht.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bisht.example.model.User;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
		LOGGER.info("handleUserNotFoundException invoked");
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({Exception.class })
	public ResponseEntity<?> handleException(RuntimeException exception) {
		LOGGER.info("handleException invoked");
		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

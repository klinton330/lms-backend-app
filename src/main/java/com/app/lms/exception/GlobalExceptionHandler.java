package com.app.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.lms.payload.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDTO> createResourceNotFoundException(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND, e.getMessage()));

	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ErrorDTO> createResourceAlreadyFoundException(ResourceAlreadyExistException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage()));

	}

	@ExceptionHandler(TokenNotValidException.class)
	public ResponseEntity<ErrorDTO> createTokenNotValidException(TokenNotValidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
	
	@ExceptionHandler(InvalidLeaveTypeException.class)
	public ResponseEntity<ErrorDTO> createInvalidLeaveTypeException(InvalidLeaveTypeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}
}

package com.easyq.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ErrorResponse> buildResponse(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(new ErrorResponse(message, status.value()));
	}

	@ExceptionHandler(AiServiceException.class)
	public ResponseEntity<ErrorResponse> handleAiException(AiServiceException ex) {
		return buildResponse(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.joining(", "));

		return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
		return buildResponse("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
